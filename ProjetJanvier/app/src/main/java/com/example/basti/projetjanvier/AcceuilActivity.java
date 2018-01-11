package com.example.basti.projetjanvier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AcceuilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        //Récupération des boutons
        Button ajoutArticle = (Button)findViewById(R.id.ajoutArticle);
        Button recherchePrix = (Button)findViewById(R.id.recherchePrix);
        Button rechercheVille = (Button)findViewById(R.id.rechercheVille);

        ajoutArticle.setOnClickListener(ajout);
        recherchePrix.setOnClickListener(prix);
        rechercheVille.setOnClickListener(ville);
    }

    //Fonction quand on clique sur le bouton ajout
    private View.OnClickListener ajout = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AcceuilActivity.this, AjoutArticle.class);
            startActivity(intent);
        }
    };

    //Fonction quand on clique sur le bouton recherchePrix
    private View.OnClickListener prix = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AcceuilActivity.this, RecherchePrix.class);
            startActivity(intent);
        }
    };

    //Fonction quand on clique sur le bouton rechercheVille
    private View.OnClickListener ville = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AcceuilActivity.this, RechercheVille.class);
            startActivity(intent);
        }
    };
}
