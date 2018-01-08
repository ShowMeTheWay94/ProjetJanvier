package com.example.basti.projetjanvier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RecherchePrix extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercheprix);

        Button cherche = (Button)findViewById(R.id.btnCherche);
        Button retour = (Button)findViewById(R.id.btnRetour);

        retour.setOnClickListener(back);
    }

    private View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecherchePrix.this, AcceuilActivity.class);
            startActivity(intent);
        }
    };
}
