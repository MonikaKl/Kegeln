package com.example.monika.kegeln.datenbank.bo;

/**
 * Created by Monika on 05.06.2016.
 */
public class Spieler {
    private long id;
    private String vorname;
    private String nachname;
    private String geburtsdatum;

    public Spieler(long id, String vorname, String nachname, String geburtsdatum){
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spieler spieler = (Spieler) o;

        return id == spieler.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return vorname + " " + nachname + " " + geburtsdatum;
    }
}
