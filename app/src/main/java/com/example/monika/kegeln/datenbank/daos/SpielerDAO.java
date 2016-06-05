package com.example.monika.kegeln.datenbank.daos;

import com.example.monika.kegeln.datenbank.bo.Spieler;

import java.util.List;
/**
 * Created by Daniel on 05.06.2016.
 */
public interface SpielerDao {

    /**
     * Erstellt einen Spieler
     * @param vorname
     * @param nachname
     * @param geburtsdatum
     * @return Spieler
     */
    public Spieler createSpieler(String vorname, String nachname, String geburtsdatum);

    /**
     * Gibt alle Spieler zurück
     * @return List<Spieler>
     */
    public List<Spieler> getAllSpieler();
}

