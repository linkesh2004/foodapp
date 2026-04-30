package com.example.foodmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {

    EditText nameInput, priceInput, descInput, ImageInput;
    Button addbtn;

    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_food);

        nameInput = findViewById(R.id.nameInput);
        priceInput = findViewById(R.id.PriceInput);
        descInput = findViewById(R.id.descInput);
        ImageInput = findViewById(R.id.imageInput);
        addbtn = findViewById(R.id.addBtn);

        db = FirebaseFirestore.getInstance();

        addbtn.setOnClickListener(v-> addFood());



    }

    private void addFood() {

        String name = nameInput.getText().toString().trim();
        String price = priceInput.getText().toString().trim();
        String desc = descInput.getText().toString().trim();
        String imageurl = ImageInput.getText().toString().trim();

        if(name.isEmpty()||price.isEmpty()){
            Toast.makeText(this, "Name and price required", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,Object> food = new HashMap<>();
        food.put("name", name);
        food.put("price", price);
        food.put("description",desc);
        food.put("imageurl",imageurl);

        db.collection("food_menu")
                .add(food)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this,"Food Added!",Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this,"Error :" + e.getMessage(),Toast.LENGTH_SHORT).show();

                });





    }

    private void clearFields() {
        nameInput.setText("");
        priceInput.setText("");
        descInput.setText("");
        ImageInput.setText("");

    }
}