package com.example.myapplication.objects;

import java.io.Serializable;

public class Kategorijos implements Serializable {
    private String pavadinimas;
    private String kurejas;
    private int id;

    public String getPavadinimas() {
        return pavadinimas;
    }
    public void setPavadinimas(String pavadinimas){
        this.pavadinimas = pavadinimas;
    }

    public String getKurejas() {
        return kurejas;
    }

    public Kategorijos(String pavadinimas, String vartotojas) {
        this.pavadinimas = pavadinimas;
        this.kurejas = vartotojas;
    }

    public Kategorijos(String pavadinimas, String kurejas, int id) {
        this.pavadinimas = pavadinimas;
        this.kurejas = kurejas;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return pavadinimas + " kurejas: " + kurejas;
    }
}