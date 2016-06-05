package com.example.monika.kegeln.datenbank.daos;

import com.example.monika.kegeln.datenbank.bo.Strafe;

import java.util.List;

/**
 * Created by Daniel on 05.06.2016.
 */
public interface StrafeDao {

    /**
     * Erstellt eine Strafe
     * @param bezeichnung
     * @param preis
     * @return Spieler
     */
    public Strafe createStrafe(String bezeichnung, double preis);

    /**
     * Gibt alle Strafen zurück
     * @return List<Strafe>
     */
    public List<Strafe> getAllStrafen();
}

