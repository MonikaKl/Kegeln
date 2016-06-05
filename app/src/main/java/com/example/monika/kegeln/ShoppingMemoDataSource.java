package com.example.monika.kegeln;

/**
 * Created by Monika on 05.06.2016.
 *  Diese Klasse ist unser Data Access Object und für das Verwalten der Daten verantwortlich.
 * Es unterhält die Datenbankverbindung und ist für das Hinzufügen, Auslesen und Löschen von
 * Datensätzen zuständig. Außerdem wandelt es Datensätze in Java-Objekte für uns um,
 * so dass der Code unserer Benutzeroberfläche nicht direkt mit den Datensätzen arbeiten muss.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class ShoppingMemoDataSource {

    private static final String LOG_TAG = ShoppingMemoDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private ShoppingMemoDbHelper dbHelper;


    public ShoppingMemoDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new ShoppingMemoDbHelper(context);
    }
}