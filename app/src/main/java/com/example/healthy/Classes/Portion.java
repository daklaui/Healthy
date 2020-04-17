package com.example.healthy.Classes;

public class Portion {
    int id ;
    String typePortion;
   String quantitéParPotion ;


    public Portion(int id, String typePortion, String quantitéParPotion) {
        this.id = id;
        this.typePortion = typePortion;
        this.quantitéParPotion = quantitéParPotion;
    }

    public Portion(String typePortion, String quantitéParPotion) {
        this.typePortion = typePortion;
        this.quantitéParPotion = quantitéParPotion;
    }

    public Portion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypePortion() {
        return typePortion;
    }

    public void setTypePortion(String typePortion) {
        this.typePortion = typePortion;
    }

    public String getQuantitéParPotion() {
        return quantitéParPotion;
    }

    public void setQuantitéParPotion(String quantitéParPotion) {
        this.quantitéParPotion = quantitéParPotion;
    }
}





