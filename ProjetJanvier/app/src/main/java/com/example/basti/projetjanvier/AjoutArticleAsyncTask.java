package com.example.basti.projetjanvier;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AjoutArticleAsyncTask extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params) {
        String data = params[0];

        try {
            URL url = new URL("http://10.0.2.2/ajoutArticles.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            OutputStream out = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();
            urlConnection.disconnect();
        } catch (Exception e) {
            Log.e("Exception asynchrone", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
