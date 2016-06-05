package com.example.monika.kegeln;

import java.util.Date;

/**
 * Created by Monika on 05.06.2016.
 */
public class Spieler {
    private String vorname;
    private String nachname;
    private Date geburtsdatum;

    public Spieler(String vorname, String nachname, Date geburtsdatum){
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
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

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
}
