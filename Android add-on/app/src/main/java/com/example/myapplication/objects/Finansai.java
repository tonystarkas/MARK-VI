package com.example.myapplication.objects;

import java.io.Serializable;

public class Finansai implements Serializable {
    double suma;
    private String pavadinimas;
    private int kategorijos_id;
    private int finansu_id;

    @Override
    public String toString() {
        return pavadinimas + " " + suma;
    }

    public Finansai(double suma, String pavadinimas, int kategorijos_id, int finansu_id) {
        this.suma = suma;
        this.pavadinimas = pavadinimas;
        this.kategorijos_id = kategorijos_id;
        this.finansu_id = finansu_id;
    }

    public int getKategorijos_id() {
        return kategorijos_id;
    }

    public int getFinansu_id() {
        return finansu_id;
    }
    public double getSuma() {
        return suma;
    }
    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public int getId() {
        return finansu_id;
    }
}
