package com.example.basti.projetjanvier;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            String result = "";
            String line = "";
            while((line = br.readLine()) != null){
                result += line + "\n";
            }
            br.close();
            is.close();
            urlConnection.disconnect();
            Log.e("PHP RESULT", result);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception asynchrone", e.getMessage());
        }
        return null;
    }
}
