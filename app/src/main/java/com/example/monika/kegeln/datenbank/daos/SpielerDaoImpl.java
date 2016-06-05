package com.example.monika.kegeln.datenbank.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.monika.kegeln.datenbank.verwaltung.KegelnDbHelper;
import com.example.monika.kegeln.datenbank.bo.Spieler;
import com.example.monika.kegeln.datenbank.verwaltung.SpielerSqlite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monika on 05.06.2016.
 */
public class SpielerDaoImpl implements SpielerDao {
    private static final String LOG_TAG = SpielerDaoImpl.class.getSimpleName();


    private KegelnDbHelper dbHelper;

    private String[] columns = {
            SpielerSqlite.COLUMN_ID,
            SpielerSqlite.COLUMN_VORNAME,
            SpielerSqlite.COLUMN_NACHNAME,
            SpielerSqlite.COLUMN_GEBURTSDATUM
    };

    public SpielerDaoImpl(Context context, KegelnDbHelper dbHelper ) {
        Log.d(LOG_TAG, "SpielerDaoImpl Konstruktor.");
        this.dbHelper = dbHelper;
    }



    /*
Datensätze einfügen
 */
    public Spieler createSpieler(String vorname, String nachname, String geburtsdatum) {
        ContentValues values = new ContentValues();
        values.put(SpielerSqlite.COLUMN_VORNAME, vorname);
        values.put(SpielerSqlite.COLUMN_NACHNAME, nachname);
        values.put(SpielerSqlite.COLUMN_GEBURTSDATUM, geburtsdatum);

        long insertId = dbHelper.getDatabase().insert(SpielerSqlite.TABLE_SPIELER, null, values);

        Cursor cursor = dbHelper.getDatabase().query(SpielerSqlite.TABLE_SPIELER,
                columns, SpielerSqlite.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Spieler spieler = cursorToSpieler(cursor);
        cursor.close();

        return spieler;
    }

    private Spieler cursorToSpieler(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(SpielerSqlite.COLUMN_ID);
        int idVorname = cursor.getColumnIndex(SpielerSqlite.COLUMN_VORNAME);
        int idNachname = cursor.getColumnIndex(SpielerSqlite.COLUMN_NACHNAME);
        int idGeburtsdatum= cursor.getColumnIndex(SpielerSqlite.COLUMN_GEBURTSDATUM);

        String vorname = cursor.getString(idVorname);
        String nachname = cursor.getString(idNachname);
        String geburtsdatum = cursor.getString(idGeburtsdatum);
        long id = cursor.getLong(idIndex);

        Spieler spieler = new Spieler(id, vorname, nachname, geburtsdatum);

        return spieler;
    }

    public List<Spieler> getAllSpieler() {
        List<Spieler> alleSpieler = new ArrayList<>();

        Cursor cursor = dbHelper.getDatabase().query(SpielerSqlite.TABLE_SPIELER,
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
