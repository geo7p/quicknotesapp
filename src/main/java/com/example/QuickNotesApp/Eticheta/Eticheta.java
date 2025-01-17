package com.example.QuickNotesApp.Eticheta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Eticheta {
    @Id
    @GeneratedValue
    private int id;
    private String denumire;

    public Eticheta() {

    }
    public Eticheta(String denumire) {
        this.denumire = denumire;
    }
    public String getDenumire() {
        return this.denumire;
    }
    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
