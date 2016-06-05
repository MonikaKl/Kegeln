package com.example.monika.kegeln.datenbank.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.monika.kegeln.datenbank.bo.Strafe;
import com.example.monika.kegeln.datenbank.verwaltung.KegelnDbHelper;
import com.example.monika.kegeln.datenbank.verwaltung.StrafeSqlite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 05.06.2016.
 */
public class StrafeDaoImpl implements StrafeDao {
    private static final String LOG_TAG = StrafeDaoImpl.class.getSimpleName();


    private KegelnDbHelper dbHelper;

    private String[] columns = {
            StrafeSqlite.COLUMN_ID,
            StrafeSqlite.COLUMN_BEZEICHNUNG,
            StrafeSqlite.COLUMN_PREIS,
    };

    public StrafeDaoImpl(Context context, KegelnDbHelper dbHelper ) {
        Log.d(LOG_TAG, "StrafeDaoImpl Konstruktor.");
        this.dbHelper = dbHelper;
    }

    @Override
    public Strafe createStrafe(String bezeichnung, double preis) {
        ContentValues values = new ContentValues();
        values.put(StrafeSqlite.COLUMN_BEZEICHNUNG, bezeichnung);
        values.put(StrafeSqlite.COLUMN_PREIS, preis);

        long insertId = dbHelper.getDatabase().insert(StrafeSqlite.TABLE_STRAFE, null, values);

        Cursor cursor = dbHelper.getDatabase().query(StrafeSqlite.TABLE_STRAFE,
                columns, StrafeSqlite.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Strafe strafe = cursorToStrafe(cursor);
        cursor.close();

        return strafe;
    }

    private Strafe cursorToStrafe(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(StrafeSqlite.COLUMN_ID);
        int idBezeichnung = cursor.getColumnIndex(StrafeSqlite.COLUMN_BEZEICHNUNG);
        int idPreis = cursor.getColumnIndex(StrafeSqlite.COLUMN_PREIS);

        String bezeichnung = cursor.getString(idBezeichnung);
        double preis = cursor.getDouble(idPreis);
        long id = cursor.getLong(idIndex);

        Strafe strafe = new Strafe(id, bezeichnung, preis);

        return strafe;
    }

    @Override
    public List<Strafe> getAllStrafen() {
        List<Strafe> alleStrafen = new ArrayList<>();

        Cursor cursor = dbHelper.getDatabase().query(StrafeSqlite.TABLE_STRAFE,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Strafe strafe;

        while(!cursor.isAfterLast()) {
            strafe = cursorToStrafe(cursor);
            alleStrafen.add(strafe);
            Log.d(LOG_TAG, "ID: " + strafe.getId() + ", Inhalt: " + strafe.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return alleStrafen;
    }
    }
