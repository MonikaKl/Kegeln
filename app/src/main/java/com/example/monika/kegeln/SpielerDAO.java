package com.example.monika.kegeln;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monika on 05.06.2016.
 */
public class SpielerDAO {
    private static final String LOG_TAG = SpielerDAO.class.getSimpleName();

    private SQLiteDatabase database;
    private KegelnDbHelper dbHelper;

    private String[] columns = {
            KegelnDbHelper.COLUMN_ID,
            KegelnDbHelper.COLUMN_VORNAME,
            KegelnDbHelper.COLUMN_NACHNAME,
            KegelnDbHelper.COLUMN_GEBURTSDATUM
    };

    public SpielerDAO(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new KegelnDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    /*
Datensätze einfügen
 */
    public Spieler createSpieler(String vorname, String nachname, String geburtsdatum) {
        ContentValues values = new ContentValues();
        values.put(KegelnDbHelper.COLUMN_VORNAME, vorname);
        values.put(KegelnDbHelper.COLUMN_NACHNAME, nachname);
        values.put(KegelnDbHelper.COLUMN_GEBURTSDATUM, geburtsdatum);

        long insertId = database.insert(KegelnDbHelper.TABLE_SPIELER, null, values);

        Cursor cursor = database.query(KegelnDbHelper.TABLE_SPIELER,
                columns, KegelnDbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Spieler spieler = cursorToSpieler(cursor);
        cursor.close();

        return spieler;
    }

    private Spieler cursorToSpieler(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(KegelnDbHelper.COLUMN_ID);
        int idVorname = cursor.getColumnIndex(KegelnDbHelper.COLUMN_VORNAME);
        int idNachname = cursor.getColumnIndex(KegelnDbHelper.COLUMN_NACHNAME);
        int idGeburtsdatum= cursor.getColumnIndex(KegelnDbHelper.COLUMN_GEBURTSDATUM);

        String vorname = cursor.getString(idVorname);
        String nachname = cursor.getString(idNachname);
        String geburtsdatum = cursor.getString(idGeburtsdatum);
        long id = cursor.getLong(idIndex);

        Spieler spieler = new Spieler(id, vorname, nachname, geburtsdatum);

        return spieler;
    }

    public List<Spieler> getAllSpieler() {
        List<Spieler> alleSpieler = new ArrayList<>();

        Cursor cursor = database.query(KegelnDbHelper.TABLE_SPIELER,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Spieler spieler;

        while(!cursor.isAfterLast()) {
            spieler = cursorToSpieler(cursor);
            alleSpieler.add(spieler);
            Log.d(LOG_TAG, "ID: " + spieler.getId() + ", Inhalt: " + spieler.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return alleSpieler;
    }



}
