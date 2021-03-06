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

public class RecherchePrix extends AppCompatActivity {
    private EditText min,max;
    private StringBuilder res;
    private RecherchePrix search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercheprix);

        //Initialise l'activité et récupère les boutons
        search = this;
        min = (EditText)findViewById(R.id.prixMin);
        max = (EditText)findViewById(R.id.prixMax);
        Button cherche = (Button)findViewById(R.id.btnCherche);
        Button retour = (Button)findViewById(R.id.btnRetour);

        if(savedInstanceState != null){
            min.setText(savedInstanceState.getString("min"));
            max.setText(savedInstanceState.getString("max"));
        }

        cherche.setOnClickListener(chercher);
        retour.setOnClickListener(back);
    }

    //Fonction quand on clique sur le bouton chercher
    private View.OnClickListener chercher = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(min != null || max != null){
                res = new StringBuilder();

                try {
                    res.append(URLEncoder.encode("prixMin", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(min.getText().toString(), "UTF-8"));
                    res.append("&");
                    res.append(URLEncoder.encode("prixMax", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(max.getText().toString(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                new RecherchePrixAsyncTask(search).execute(res.toString());
            }
            else{
                Toast.makeText(v.getContext(),"Veuillez remplir les deux champs", Toast.LENGTH_LONG).show();
            }
        }
    };

    //Fonction quand on clique sur le bouton retour
    private View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecherchePrix.this, AcceuilActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putString("min", min.getText().toString());
        saveInstanceState.putString("max", max.getText().toString());
    }
}
