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

    import com.example.monika.kegeln.datenbank.bo.Strafe;
    import com.example.monika.kegeln.datenbank.daos.StrafeDaoImpl;
    import com.example.monika.kegeln.datenbank.verwaltung.KegelnDbHelper;

    import java.util.List;

    /**
     * Created by Daniel on 05.06.2016.
     */
    public class Strafenverwaltung extends AppCompatActivity{

        public static final String LOG_TAG = Strafenverwaltung.class.getSimpleName();

        public StrafeDaoImpl strafeDao;

        private KegelnDbHelper kegelnDbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.strafenverwaltung);

            Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");

            kegelnDbHelper = new KegelnDbHelper(this);

            activateButtonStrafeHinzufuegen();
        }

        private void activateButtonStrafeHinzufuegen() {
            Button buttonStrafeHinzufuegen = (Button) findViewById(R.id.button_strafe_hinzufuegen);
            final EditText editTextBezeichnung = (EditText) findViewById(R.id.eingabe_bezeichnung);
            final EditText editTextPreis = (EditText) findViewById(R.id.eingabe_preis);

            assert buttonStrafeHinzufuegen != null;
            buttonStrafeHinzufuegen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String bezeichnung = editTextBezeichnung.getText().toString();
                    double preis =Double.valueOf(editTextPreis.getText().toString());

                    if(TextUtils.isEmpty(bezeichnung)) {
                        editTextBezeichnung.setError(getString(R.string.editText_errorMessage));
                        return;
                    }
                    if(TextUtils.isEmpty(Double.valueOf(preis).toString())) {
                        editTextPreis.setError(getString(R.string.editText_errorMessage));
                        return;
                    }


                    editTextBezeichnung.setText("");
                    editTextPreis.setText("");

                    strafeDao.createStrafe(bezeichnung, preis);

                    InputMethodManager inputMethodManager;
                    inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    if(getCurrentFocus() != null) {
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                    zeigeAlleStrafen();
                }
            });

        }

        private void zeigeAlleStrafen() {
            strafeDao = new StrafeDaoImpl(this, kegelnDbHelper);
            List<Strafe> alleStrafeListe = strafeDao.getAllStrafen();

            ArrayAdapter<Strafe> strafeArrayAdapter = new ArrayAdapter<> (
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    alleStrafeListe);

            ListView strafenListView = (ListView) findViewById(R.id.listview_alle_strafen);
            strafenListView.setAdapter(strafeArrayAdapter);
        }

        @Override
        protected void onResume() {
            super.onResume();

            Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
            kegelnDbHelper.open();

            Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
            zeigeAlleStrafen();
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

