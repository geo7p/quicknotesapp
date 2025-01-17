package com.example.QuickNotesApp.Notita;

import com.example.QuickNotesApp.Eticheta.Eticheta;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Notita {
    @Id
    @GeneratedValue
    private int id;
    private String titlu;
    private String subtitlu;
    private String continut;
    private boolean isArchived;
    private String textArhivat;
    private String butonArhivat;
    private boolean isPinned;
    private String textPin;
    private String butonPin;
    private String culoare;
    @ElementCollection
    private List<Eticheta> etichete;

    public Notita() {
    }

    public Notita(String titlu, String subtitlu, String continut, String culoare) {
        this.titlu = titlu;
        this.subtitlu = subtitlu;
        this.continut = continut;
        this.isArchived = false;
        this.textArhivat = "";
        this.butonArhivat = "ARHIVEAZA";
        this.textPin = "";
        this.isPinned = false;
        this.butonPin = "PIN";
        this.culoare = culoare;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return this.titlu;
    }
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getSubtitlu() {
        return this.subtitlu;
    }

    public void setSubtitlu(String subtitlu) {
        this.subtitlu = subtitlu;
    }

    public String getContinut() {
        return this.continut;
    }
    public void setContinut(String continut) {
        this.continut = continut;
    }

    public boolean getArchived() {
        return isArchived;
    }
    public void setArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public String getTextArhivat() {
        return textArhivat;
    }
    public void setTextArhivat(String textArhivat) {
        this.textArhivat = textArhivat;
    }

    public String getButonArhivat() {
        return this.butonArhivat;
    }
    public void setButonArhivat(String butonArhivat) {
        this.butonArhivat = butonArhivat;
    }

    public boolean getIsPinned() {
        return isPinned;
    }
    public void setIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public String getTextPin() {
        return textPin;
    }
    public void setTextPin(String textPin) {
        this.textPin = textPin;
    }

    public String getButonPin() {
        return butonPin;
    }
    public void setButonPin(String butonPin) {
        this.butonPin = butonPin;
    }

    public String getCuloare() {
        return this.culoare;
    }
    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public List<Eticheta> getEtichete() {
        return this.etichete;
    }
    public void adaugaEticheta(Eticheta e) {
        for(Eticheta et : this.etichete) {
            if(!et.equals(e))
                this.etichete.add(e);
        }
    }
    public void stergeEticheta(Eticheta e) {
        this.etichete.remove(e);
    }
}
