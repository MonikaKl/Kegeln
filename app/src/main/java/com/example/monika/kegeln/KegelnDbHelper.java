package com.example.monika.kegeln;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Monika on 05.06.2016.
 * Sie ist eine Hilfsklasse mit deren Hilfe wir die SQLite-Datenbank erstellen lassen.
 * Sie enthält weiterhin wichtige Konstanten, die wir für die Arbeit mit der Datenbank benötigen,
 * wie den Tabellennamen, die Datenbankversion oder die Namen der Spalten.
 */
public class KegelnDbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = KegelnDbHelper.class.getSimpleName();

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
                    COLUMN_VORNAME + " TEXT NOT NULL, " +
                    COLUMN_GEBURTSDATUM + " DATE NOT NULL);";

    public KegelnDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    /*
     * Methode wird nur einmal aufgerufen, wenn die Datenbank noch nicht erzeugt ist.
     * Gibt es die Datenbank schon, wird die Methode nicht ausgeführt
     * Zum Erzeugen von Tabellen benutzen
     */
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}