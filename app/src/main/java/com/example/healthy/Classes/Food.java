package com.example.healthy.Classes;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String title;
    private int calories;
private String unite;

    public String getQnparUnite() {
        return QnparUnite;
    }

    public void setQnparUnite(String qnparUnite) {
        QnparUnite = qnparUnite;
    }

    private String QnparUnite;
    public Food() {
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Food(String title, int calories, String unite, String qnparUnite, String date, String image) {
        this.title = title;
        this.calories = calories;
        this.unite = unite;
        QnparUnite = qnparUnite;
        this.image = image;
        Date = date;
    }

    private String carbs;
    private String fat;
    private String protein;
    private String image;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    private String Date;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int  getCalories() {
        return calories;
    }

    public void setCalories(int  calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}