package com.example.basti.projetjanvier;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private Button suivant,precedent,tri,retour;
    private TableLayout listeArticle;
    private boolean asc,isresuming;
    private String data;
    private ArrayList<String> articles;
    private String[][] article;
    private Bundle items;
    private int interval,firstArt,tailleEcran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultactivity);

        //Récupération des boutons
        suivant = (Button)findViewById(R.id.suivant);
        precedent = (Button)findViewById(R.id.precedent);
        tri = (Button)findViewById(R.id.tri);
        retour = (Button)findViewById(R.id.btnRetour);
        listeArticle = (TableLayout)findViewById(R.id.listeArticle);

        //Récupération de la taille de l'écran
        tailleEcran = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if(savedInstanceState != null){
            asc = savedInstanceState.getBoolean("asc");
            articles = savedInstanceState.getStringArrayList("articles");
            interval = savedInstanceState.getInt("interval");

            article = new String[articles.size() / 6][6];
        }
        else{
            asc = true;
            items = getIntent().getExtras();
            articles = items.getStringArrayList("items");
            article = new String[articles.size() / 6][6];
            if (article.length < 5)
                interval = article.length;
            else
                interval = 5;
        }

        //Récupération des données
        data = items.getString("data");
        isresuming = false;

        int j = 0;
        for (int i = 0; i < articles.size(); i = i + 6) {
            article[j][0] = articles.get(i);
            article[j][1] = articles.get(i + 1);
            article[j][2] = articles.get(i + 2);
            article[j][3] = articles.get(i + 3);
            article[j][4] = articles.get(i + 4);
            article[j][5] = articles.get(i + 5);
            j++;
        }

        //On vérifie si la liste n'est pas vide
        if (interval == 0) {
            TableRow tr = new TableRow(this);
            TextView tv = new TextView(this);
            tv.setText(R.string.listeVide);
            tv.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            tv.setPadding(10, 10, 10, 10);
            tr.addView(tv);
            listeArticle.addView(tr);
        } else {
            sortASC();
            firstArt = 0;
            fill(interval);
            activerBtns();
        }

        suivant.setOnClickListener(suiv);
        precedent.setOnClickListener(prec);
        tri.setOnClickListener(triListe);
        retour.setOnClickListener(back);
    }

    //Fonction pour activer les boutons
    public void activerBtns(){
        if(firstArt < 5){
            precedent.setEnabled(false);
            if(article.length > 5)
                suivant.setEnabled(true);
            else
                suivant.setEnabled(false);
        }
        else if(firstArt >= article.length-5){
            suivant.setEnabled(false);
            if(article.length > 5)
                precedent.setEnabled(true);
            else
                precedent.setEnabled(false);
        }
        else{
            if(article.length > 10) {
                suivant.setEnabled(true);
                precedent.setEnabled(true);
            }
            else {
                suivant.setEnabled(false);
                precedent.setEnabled(false);
            }
        }
    }

    //Fonction pour trier et swap les articles
    public void sortASC(){
        int max_i;
        for(int i = article.length; i > 1; i--){
            max_i = max(i);
            swap(i-1, max_i);
        }
    }

    void swap(int x, int y)
    {
        String[] tmp = new String[6];

        tmp[0] = article[x][0];
        tmp[1] = article[x][1];
        tmp[2] = article[x][2];
        tmp[3] = article[x][3];
        tmp[4] = article[x][4];
        tmp[5] = article[x][5];

        article[x][0] = article[y][0];
        article[x][1] = article[y][1];
        article[x][2] = article[y][2];
        article[x][3] = article[y][3];
        article[x][4] = article[y][4];
        article[x][5] = article[y][5];

        article[y][0] = tmp[0];
        article[y][1] = tmp[1];
        article[y][2] = tmp[2];
        article[y][3] = tmp[3];
        article[y][4] = tmp[4];
        article[y][5] = tmp[5];
    }

    public int max(int length)
    {
        int i=0, indice_max=0;

        while(i < length)
        {
            if(article[i][0].compareTo(article[indice_max][0]) > 0)
                indice_max = i;
            i++;
        }

        return indice_max;
    }

    //Fonction pour retourner le tableau
    public void reverse(){
        for(int i = 0; i < article.length/2; i++){
            swap(i, article.length-(i+1));
        }
    }

    //Fonction pour remplir le tableau
    public void fill(int end){
        listeArticle.removeAllViews();
        TableRow tr;
        TextView nom;
        TextView prix;
        TextView ville;
        TextView etat;

        tr = new TableRow(this);

        if(tailleEcran == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            nom = new TextView(this);
            nom.setText(R.string.nomArticle);
            nom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            nom.setPadding(10,10,50,10);
            tr.addView(nom);

            prix = new TextView(this);
            prix.setText(R.string.prix);
            prix.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            prix.setPadding(10,10,50,10);
            tr.addView(prix);

            ville = new TextView(this);
            ville.setText(R.string.ville);
            ville.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            ville.setPadding(10,10,50,10);
            tr.addView(ville);

            etat = new TextView(this);
            etat.setText(R.string.etat);
            etat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            etat.setPadding(10,10,50,10);
            tr.addView(etat);
        }
        else{
            nom = new TextView(this);
            nom.setText(R.string.nomArticle);
            nom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            nom.setPadding(10,10,50,10);
            tr.addView(nom);

            prix = new TextView(this);
            prix.setText(R.string.prix);
            prix.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            prix.setPadding(10,10,50,10);
            tr.addView(prix);

            ville = new TextView(this);
            ville.setText(R.string.ville);
            ville.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            ville.setPadding(10,10,50,10);
            tr.addView(ville);

            etat = new TextView(this);
            etat.setText(R.string.etat);
            etat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            etat.setPadding(10,10,50,10);
            tr.addView(etat);
        }

        listeArticle.addView(tr);

        for(int i = firstArt; i < firstArt+end; i++){
            tr = new TableRow(this);

            if(tailleEcran == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                nom = new TextView(this);
                nom.setText(article[i][0]);
                nom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                nom.setPadding(10, 10, 50, 10);
                tr.addView(nom);

                prix = new TextView(this);
                prix.setText(article[i][1]);
                prix.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                prix.setPadding(10, 10, 50, 10);
                tr.addView(prix);

                ville = new TextView(this);
                ville.setText(article[i][2]);
                ville.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                ville.setPadding(10, 10, 50, 10);
                tr.addView(ville);

                etat = new TextView(this);
                etat.setText(article[i][3]);
                etat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                etat.setPadding(10, 10, 50, 10);
                tr.addView(etat);
            }
            else{
                nom = new TextView(this);
                nom.setText(article[i][0]);
                nom.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                nom.setPadding(10, 10, 50, 10);
                tr.addView(nom);

                prix = new TextView(this);
                prix.setText(article[i][1]);
                prix.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                prix.setPadding(10, 10, 50, 10);
                tr.addView(prix);

                ville = new TextView(this);
                ville.setText(article[i][2]);
                ville.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                ville.setPadding(10, 10, 50, 10);
                tr.addView(ville);

                etat = new TextView(this);
                etat.setText(article[i][3]);
                etat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                etat.setPadding(10, 10, 50, 10);
                tr.addView(etat);
            }
            listeArticle.addView(tr);
        }
    }

    //Fonction quand on clique sur suivant
    private View.OnClickListener suiv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            interval = -1;
            firstArt += 5;
            if(firstArt <= article.length && firstArt > article.length-5)
                interval = article.length - firstArt;
            if(interval == -1) {
                interval = 5;
                firstArt -= 5;
            }
            fill(interval);
            activerBtns();
        }
    };

    //Fonction quand on clique sur précédent
    private View.OnClickListener prec = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            firstArt -= 5;
            interval = 5;
            fill(interval);
            activerBtns();
        }
    };

    //Fonction quand on clique sur ascendant ou descendant
    private View.OnClickListener triListe = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            reverse();
            if(asc) {
                TextView tmp = (TextView) v;
                tmp.setText(R.string.triDescendant);
                asc = false;
            }
            else{
                TextView tmp = (TextView) v;
                tmp.setText(R.string.triAscendant);
                asc = true;
            }
            fill(interval);
            activerBtns();
        }
    };

    //Fonction quand on clique sur retour
    private View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ResultActivity.this, AcceuilActivity.class);
            startActivity(intent);
        }
    };

    //Fonction pour sauver l'instance
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putBoolean("asc", asc);
        saveInstanceState.putStringArrayList("articles",articles);
        saveInstanceState.putInt("interval",interval);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isresuming = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isresuming) {
            new RechercheVilleAsyncTask(this).execute(data);
            isresuming = false;
        }
    }
}
