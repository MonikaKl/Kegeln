package com.example.monika.kegeln;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Daniel on 06.06.2016.
 */
public class KegelnStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegelnstart);
        Button spielerVerwaltungsButton = (Button) findViewById(R.id.button_spielerverwaltung);
        assert spielerVerwaltungsButton != null;
        spielerVerwaltungsButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(KegelnStart.this, Spielerverwaltung.class));

            }
        });

        Button btn = (Button) findViewById(R.id.button_strafenverwaltung);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                startActivity(new Intent(KegelnStart.this, Strafenverwaltung.class));

            }
        });


    }
}