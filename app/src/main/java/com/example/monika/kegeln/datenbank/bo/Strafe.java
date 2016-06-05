package com.example.monika.kegeln.datenbank.bo;

/**
 * Created by Daniel on 05.06.2016.
 */
public class Strafe {
    private long id;
    private String bezeichnung;
    private double preis;

    public Strafe(long id, String bezeichnung, double preis) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Strafe strafe = (Strafe) o;

        return id == strafe.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
         return bezeichnung + " " + preis + "€";
    }
}
