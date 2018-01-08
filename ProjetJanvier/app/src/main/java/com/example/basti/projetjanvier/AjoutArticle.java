package com.example.basti.projetjanvier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AjoutArticle extends AppCompatActivity {
    private Button ajout,retour;
    private EditText nom,desc,prix,ville;
    private RadioGroup etat,info;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutarticle);

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

    private View.OnClickListener add = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(nom.getText().equals("") || desc.getText().equals("") || prix.getText().equals("") || etat.getCheckedRadioButtonId() == -1 ||
                    ville.getText().equals("") || info.getCheckedRadioButtonId() == -1){
                Toast.makeText(v.getContext(),R.string.erreurChampManquant,Toast.LENGTH_SHORT).show();
            }
            else{
                res += "nom=" + nom.getText();
                res += "&desc=" + desc.getText();
                res += "&prix=" + prix.getText();
                if(etat.getCheckedRadioButtonId() == R.id.radioNeuf){
                    res += "&etat=1";
                }
                else{
                    res += "&etat=2";
                }
                res += "&ville=" + ville.getText();
                if(info.getCheckedRadioButtonId() == R.id.radioEnvoyer){
                    res += "&info=1";
                }
                else{
                    res += "&info=2";
                }

                new AjoutArticleAsyncTask().execute(res);

                Intent intent = new Intent(AjoutArticle.this, AcceuilActivity.class);
                startActivity(intent);
            }
        }
    };

    private View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AjoutArticle.this, AcceuilActivity.class);
            startActivity(intent);
        }
    };
}
