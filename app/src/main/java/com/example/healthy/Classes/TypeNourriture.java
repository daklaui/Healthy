package com.example.healthy.Classes;

public class TypeNourriture {
    int id ;
    String nomtype;

    public TypeNourriture(int id, String nomtype) {
        this.id = id;
        this.nomtype = nomtype;
    }

    public TypeNourriture(String nomtype) {
        this.nomtype = nomtype;
    }

    public TypeNourriture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomtype() {
        return nomtype;
    }

    public void setNomtype(String nomtype) {
        this.nomtype = nomtype;
    }
}
