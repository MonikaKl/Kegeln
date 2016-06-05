

package com.example.monika.kegeln;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.inputmethod.InputMethodManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.monika.kegeln.datenbank.daos.SpielerDaoImpl;
import com.example.monika.kegeln.datenbank.bo.Spieler;
import com.example.monika.kegeln.datenbank.verwaltung.KegelnDbHelper;

import java.util.List;

/**
 * Created by Monika on 05.06.2016.
 */


public class Spielerverwaltung extends AppCompatActivity{

    public static final String LOG_TAG = Spielerverwaltung.class.getSimpleName();

    public SpielerDaoImpl spielerDao;

    private KegelnDbHelper kegelnDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielerverwaltung);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");

        //spielerDao = new SpielerDaoImpl(this);

        kegelnDbHelper = new KegelnDbHelper(this);

        activateButtonSpielerHinzufuegen();
    }

    private void activateButtonSpielerHinzufuegen() {
        Button buttonSpielerHinzufuegen = (Button) findViewById(R.id.button_spieler_hinzufuegen);
        final EditText editTextVorname = (EditText) findViewById(R.id.eingabe_vorname);
        final EditText editTextNachname = (EditText) findViewById(R.id.eingabe_nachname);
        final EditText editTextGeburtsdatum = (EditText) findViewById(R.id.eingabe_geburtsdatum);

        assert buttonSpielerHinzufuegen != null;
        buttonSpielerHinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String vorname = editTextVorname.getText().toString();
                String nachname = editTextNachname.getText().toString();
                String geburtsdatum = editTextGeburtsdatum.getText().toString();

                if(TextUtils.isEmpty(vorname)) {
                    editTextVorname.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(nachname)) {
                    editTextNachname.setError(getString(R.string.editText_errorMessage));
                    return;
                }

                if(TextUtils.isEmpty(geburtsdatum)) {
                    editTextGeburtsdatum.setError(getString(R.string.editText_errorMessage));
                    return;
                }

                editTextVorname.setText("");
                editTextNachname.setText("");
                editTextGeburtsdatum.setText("");

                spielerDao.createSpieler(vorname, nachname, geburtsdatum);

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                zeigeAlleSpieler();
            }
        });

    }

    private void zeigeAlleSpieler () {
        spielerDao = new SpielerDaoImpl(this, kegelnDbHelper);
        List<Spieler> alleSpielerListe = spielerDao.getAllSpieler();

        ArrayAdapter<Spieler> spielerArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                alleSpielerListe);

        ListView spielerListView = (ListView) findViewById(R.id.listview_alle_spieler);
        spielerListView.setAdapter(spielerArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        kegelnDbHelper.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        zeigeAlleSpieler();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        kegelnDbHelper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

