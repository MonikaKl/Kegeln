package com.example.monika.kegeln.datenbank.verwaltung;

/**
 * Created by Daniel on 05.06.2016.
 */
public class SpielerSqlite {

    private static final String LOG_TAG = SpielerSqlite.class.getSimpleName();

    public static final String DB_NAME = "kegeln.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_SPIELER = "spieler";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_VORNAME = "vorname";
    public static final String COLUMN_NACHNAME = "nachname";
    public static final String COLUMN_GEBURTSDATUM = "geburtsdatum";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_SPIELER +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_VORNAME + " TEXT NOT NULL, " +
                    COLUMN_NACHNAME + " TEXT NOT NULL, " +
                    COLUMN_GEBURTSDATUM + " TEXT NOT NULL);";
}
