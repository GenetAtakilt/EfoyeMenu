package com.example.yilma.efoymenu;

/**
 * Created by Yilma on 09-May-16.
 */
public class Food {

    String Foodname;
    int category;
    String price;
    String description;
    int image;

    public Food(String Foodname, int category, String description, int image, String price) {
        this.Foodname = Foodname;
        this.category = category;
        this.description = description;
        this.image=image;
        this.price=price;
    }

    public String getFoodName() {
        return Foodname;
    }
    public int getCategory() {
        return category;
    }
    public String getPrice() {
        return price;
    }
    public int getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }

}
