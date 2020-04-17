package com.example.healthy.Classes;

public class Historique_Regime {
    public Historique_Regime(int id, String date, double new_Poids, double evolution, double new_IMC) {
        this.id = id;
        Date = date;
        New_Poids = new_Poids;
        Evolution = evolution;
        New_IMC = new_IMC;
    }

    public Historique_Regime() {

    }

    int id;
    String Date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getNew_Poids() {
        return New_Poids;
    }

    public void setNew_Poids(double new_Poids) {
        New_Poids = new_Poids;
    }

    public double getEvolution() {
        return Evolution;
    }

    public void setEvolution(double evolution) {
        Evolution = evolution;
    }

    public double getNew_IMC() {
        return New_IMC;
    }

    public void setNew_IMC(double new_IMC) {
        New_IMC = new_IMC;
    }

    double New_Poids;
    double Evolution;
    double New_IMC;

}
