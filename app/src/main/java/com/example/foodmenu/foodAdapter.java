package com.example.foodmenu;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class foodAdapter extends RecyclerView.Adapter<foodAdapter.ViewHolder> {
    Context context;

    List<Food> foodList;

    public foodAdapter(Context context, List<Food> foodList){
        this.context = context;
        this.foodList = foodList;
    }



    public static class  ViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, desc;
        ImageView image;


        public ViewHolder(View itemView){

            super(itemView);

            name = itemView.findViewById(R.id.foodname);
            price = itemView.findViewById(R.id.foodPrice);
            desc = itemView.findViewById(R.id.foodDesc);
            image = itemView.findViewById(R.id.foodimage);



        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Food food = foodList.get(position);
        holder.name.setText(food.name);
        holder.price.setText(food.price);
        holder.desc.setText(food.getDescription());
        Glide.with(context)
                .load(food.getImageurl())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.image);

    }

    @Override
    public int getItemCount(){
        return foodList.size();
    }



}
