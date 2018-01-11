package com.example.basti.projetjanvier;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RechercheVille extends AppCompatActivity {
    private EditText ville;
    private StringBuilder res;
    private RechercheVille search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercheville);

        //Initialise l'activité et récupère les boutons
        search = this;
        ville = (EditText)findViewById(R.id.villeRecherche);
        Button cherche = (Button)findViewById(R.id.btnCherche);
        Button retour = (Button)findViewById(R.id.btnRetour);

        if(savedInstanceState != null){
            ville.setText(savedInstanceState.getString("ville"));
        }

        cherche.setOnClickListener(chercher);
        retour.setOnClickListener(back);
    }

    //Fonction quand on clique sur le bouton chercher
    private View.OnClickListener chercher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ville != null) {
                res = new StringBuilder();

                try {
                    res.append(URLEncoder.encode("ville", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(ville.getText().toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                new RechercheVilleAsyncTask(search).execute(res.toString());
            }
            else{
                Toast.makeText(v.getContext(),"Veuillez rentrer une ville",Toast.LENGTH_LONG).show();
            }
        }
    };

    //Fonction quand on clique sur le bouton retour
    private View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RechercheVille.this, AcceuilActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putString("ville", ville.getText().toString());
    }
}
