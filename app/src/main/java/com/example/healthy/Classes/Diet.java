package com.example.healthy.Classes;

public class Diet {
    int id ;
    String type ;
    int calories ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Diet(int id, String type, int calories) {
        this.id = id;
        this.type = type;
        this.calories = calories;
    }

    public Diet(String type, int calories) {
        this.type = type;
        this.calories = calories;
    }

    public Diet() {
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> github/master
