package com.example.monika.kegeln.datenbank.verwaltung;

/**
 * Created by Daniel on 05.06.2016.
 */
public class StrafeSqlite {
    private static final String LOG_TAG = StrafeSqlite.class.getSimpleName();

    public static final String TABLE_STRAFE = "strafe";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BEZEICHNUNG = "bezeichnung";
    public static final String COLUMN_PREIS = "preis";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_STRAFE +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BEZEICHNUNG + " TEXT NOT NULL, " +
                    COLUMN_PREIS + " DOUBLE NOT NULL);";
}
