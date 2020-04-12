package com.example.healthy.Classes;

public class Nourriture {
    int id;
    String nom ;
    String type ;
    int calories ;
    float graisse ; //g
    float Cholesterol ; //mg
    float Sodium ; //mg
    float sucres ; //g
    float protein ; //g
    float  VitaminA ; //%
    float vitaminC ; //%
    float fer ; //%
    float calcium ; //%
    String typePortion;
    String quantitéParPortion;

    public Nourriture(int id, String nom, String type, int calories, float graisse, float cholesterol, float sodium, float sucres, float protein, float vitaminA, float vitaminC, float fer, float calcium, String typePortion, String quantitéParPortion) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.calories = calories;
        this.graisse = graisse;
        Cholesterol = cholesterol;
        Sodium = sodium;
        this.sucres = sucres;
        this.protein = protein;
        VitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.fer = fer;
        this.calcium = calcium;
        this.typePortion = typePortion;
        this.quantitéParPortion = quantitéParPortion;
    }


    public Nourriture(String nom, String type, int calories, float graisse, float cholesterol, float sodium, float sucres, float protein, float vitaminA, float vitaminC, float fer, float calcium, String typePortion,String quantitéParPortion) {
        this.nom = nom;
        this.type = type;
        this.calories = calories;
        this.graisse = graisse;
        Cholesterol = cholesterol;
        Sodium = sodium;
        this.sucres = sucres;
        this.protein = protein;
        VitaminA = vitaminA;
        this.vitaminC = vitaminC;
        this.fer = fer;
        this.calcium = calcium;
        this.typePortion = typePortion;
        this.quantitéParPortion = quantitéParPortion;
    }


    public Nourriture(String nom, String type, int calories, String typePortion, String quantitéParPortion) {
        this.nom = nom;
        this.type = type;
        this.calories = calories;
        this.typePortion = typePortion;
        this.quantitéParPortion = quantitéParPortion;
        this.graisse = 0 ;
        this.Cholesterol = 0 ;
        this.Sodium = 0 ;
        this.sucres = 0 ;
        this.protein = 0 ;
        this.VitaminA = 0 ;
        this.vitaminC = 0 ;
        this.fer = 0 ;
        this.calcium = 0 ;
    }

    public Nourriture() {
    }

    public String getTypePortion() {
        return typePortion;
    }

    public void setTypePortion(String typePortion) {
        this.typePortion = typePortion;
    }

    public String getQuantitéParPortion() {
        return quantitéParPortion;
    }

    public void setQuantitéParPortion(String quantitéParPortion) {
        this.quantitéParPortion = quantitéParPortion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public float getGraisse() {
        return graisse;
    }

    public void setGraisse(float graisse) {
        this.graisse = graisse;
    }

    public float getCholesterol() {
        return Cholesterol;
    }

    public void setCholesterol(float cholesterol) {
        Cholesterol = cholesterol;
    }

    public float getSodium() {
        return Sodium;
    }

    public void setSodium(float sodium) {
        Sodium = sodium;
    }

    public float getSucres() {
        return sucres;
    }

    public void setSucres(float sucres) {
        this.sucres = sucres;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getVitaminA() {
        return VitaminA;
    }

    public void setVitaminA(float vitaminA) {
        VitaminA = vitaminA;
    }

    public float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public float getFer() {
        return fer;
    }

    public void setFer(float fer) {
        this.fer = fer;
    }

    public float getCalcium() {
        return calcium;
    }

    public void setCalcium(float calcium) {
        this.calcium = calcium;
    }
}
