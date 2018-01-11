package com.example.basti.projetjanvier;

import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AjoutArticle extends AppCompatActivity {
    private Button ajout,retour;
    private EditText nom,desc,prix,ville;
    private RadioGroup etat,info;
    private StringBuilder res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutarticle);

        //Récupération des objets
        ajout = (Button)findViewById(R.id.btnAjout);
        retour = (Button)findViewById(R.id.btnRetour);
        nom = (EditText)findViewById(R.id.nomArticle);
        desc = (EditText)findViewById(R.id.descriptif);
        prix = (EditText)findViewById(R.id.prix);
        etat = (RadioGroup)findViewById(R.id.rgetat);
        ville = (EditText)findViewById(R.id.ville);
        info = (RadioGroup)findViewById(R.id.rginfo);

        if(savedInstanceState != null) {
            nom.setText(savedInstanceState.getString("nom"));
            desc.setText(savedInstanceState.getString("desc"));
            prix.setText(savedInstanceState.getString("prix"));
            if(savedInstanceState.getString("etat") == "1"){
                etat.check(R.id.radioNeuf);
            }
            else{
                etat.check(R.id.radioUse);
            }
            ville.setText(savedInstanceState.getString("ville"));
            if(savedInstanceState.getString("info") == "1"){
                etat.check(R.id.radioEnvoyer);
            }
            else{
                etat.check(R.id.radioMain);
            }
        }

        ajout.setOnClickListener(add);
        retour.setOnClickListener(back);
    }

    //Fonction quand on clique sur le bouton ajouter
    private View.OnClickListener add = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(nom.getText().equals("") || desc.getText().equals("") || prix.getText().equals("") || etat.getCheckedRadioButtonId() == -1 ||
                    ville.getText().equals("") || info.getCheckedRadioButtonId() == -1){
                Toast.makeText(v.getContext(),R.string.erreurChampManquant,Toast.LENGTH_SHORT).show();
            }
            else{
                try {
                    res = new StringBuilder();
                    res.append(URLEncoder.encode("nom", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(nom.getText().toString(), "UTF-8"));
                    res.append("&");
                    res.append(URLEncoder.encode("desc", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(desc.getText().toString(), "UTF-8"));
                    res.append("&");
                    res.append(URLEncoder.encode("prix", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(prix.getText().toString(), "UTF-8"));
                    res.append("&");
                    res.append(URLEncoder.encode("etat", "UTF-8"));
                    res.append("=");
                    if(etat.getCheckedRadioButtonId() == R.id.radioNeuf){
                        res.append(URLEncoder.encode("1", "UTF-8"));
                    }
                    else{
                        res.append(URLEncoder.encode("2", "UTF-8"));
                    }
                    res.append("&");
                    res.append(URLEncoder.encode("ville", "UTF-8"));
                    res.append("=");
                    res.append(URLEncoder.encode(ville.getText().toString(), "UTF-8"));
                    res.append("&");
                    res.append(URLEncoder.encode("info", "UTF-8"));
                    res.append("=");
                    if(info.getCheckedRadioButtonId() == R.id.radioEnvoyer){
                        res.append(URLEncoder.encode("1", "UTF-8"));
                    }
                    else{
                        res.append(URLEncoder.encode("2", "UTF-8"));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                new AjoutArticleAsyncTask().execute(res.toString());

                Intent intent = new Intent(AjoutArticle.this, AcceuilActivity.class);
                startActivity(intent);
            }
        }
    };

    //Fonction quand on clique sur le bouton retour
    private View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AjoutArticle.this, AcceuilActivity.class);
            startActivity(intent);
        }
    };
}
