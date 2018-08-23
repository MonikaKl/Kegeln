package com.example.monika.kegeln;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Daniel on 06.06.2016.
 */
public class SpielStarten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spielstart);
        Button neuesSpielStartenButton = (Button) findViewById(R.id.button_neues_spiel_starten);
        assert neuesSpielStartenButton != null;
        neuesSpielStartenButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

              //  startActivity(new Intent(SpielStarten.this, null));

            }
        });

        Button spielFortsetzenButton = (Button) findViewById(R.id.button_spiel_fortsetzen);
        assert spielFortsetzenButton != null;
        spielFortsetzenButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                //  startActivity(new Intent(SpielStarten.this, null));

            }
        });


    }
}