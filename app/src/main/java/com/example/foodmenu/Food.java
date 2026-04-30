package com.example.foodmenu;

public class Food {
    String name;
    String price, description, imageurl;
    public Food(){

    }



    public Food(String name, String price, String description,String imageurl){
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageurl = imageurl;

    }

    public String getName() {
        return name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getPrice() {
        return price;
    }


    public String getDescription() {
        return description;
    }
}
