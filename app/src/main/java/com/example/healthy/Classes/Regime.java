package com.example.healthy.Classes;

public class Regime {
    int id;
    int objectif;

    public String getTyp_regime() {
        return typ_regime;
    }

    public void setTyp_regime(String typ_regime) {
        this.typ_regime = typ_regime;
    }

    String typ_regime;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getObjectif() {
        return objectif;
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
    }

    public int getDegre_act() {
        return degre_act;
    }

    public void setDegre_act(int degre_act) {
        this.degre_act = degre_act;
    }

    public double getNew_poids() {
        return new_poids;
    }

    public void setNew_poids(double new_poids) {
        this.new_poids = new_poids;
    }

    int degre_act;
    double new_poids;

    public Regime() {

    }

    public Regime(int id, int objectif, String typ_regime, int degre_act, double new_poids) {
        this.id = id;
        this.objectif = objectif;
        this.typ_regime = typ_regime;
        this.degre_act = degre_act;
        this.new_poids = new_poids;
    }
}
