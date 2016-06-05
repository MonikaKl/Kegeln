package com.example.monika.kegeln.datenbank.verwaltung;

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

    private SQLiteDatabase database;
    private static final String LOG_TAG = KegelnDbHelper.class.getSimpleName();



    public KegelnDbHelper(Context context) {
        super(context, SpielerSqlite.DB_NAME, null, SpielerSqlite.DB_VERSION);
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
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SpielerSqlite.SQL_CREATE + " angelegt.");
            db.execSQL(SpielerSqlite.SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + StrafeSqlite.SQL_CREATE + " angelegt.");
            db.execSQL(StrafeSqlite.SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        this.database = super.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        super.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDatabase() {
        return database;
    }


}