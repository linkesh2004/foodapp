package com.example.foodmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    foodAdapter adapter;
    Button addfood;
    ArrayList<Food> foodList;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        foodList = new ArrayList<>();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button addfood = findViewById(R.id.addfoodbtn);
        addfood.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddFoodActivity.class));
        });




        adapter = new foodAdapter(this, foodList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadFoodData();
    }

    private void loadFoodData() {
        db.collection("food_menu").addSnapshotListener(((value, error) -> {
            if(error!= null) return;
            foodList.clear();
            for(QueryDocumentSnapshot doc :value){
                Food food = doc.toObject(Food.class);
                foodList.add(food);
            }
            adapter.notifyDataSetChanged();
        }));
    }
}