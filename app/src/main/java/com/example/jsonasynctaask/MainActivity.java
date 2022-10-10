package com.example.jsonasynctaask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadNote downloadNote=new DownloadNote();

        downloadNote.execute("https://newsapi.org/v2/everything?domains=wsj.com&apiKey=b44485c7d177433184e44ac0a60797bb");
            }


    public class DownloadNote extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char ch = (char) data;
                    result += ch;
                    data = inputStreamReader.read();
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // Log.i("result",s);

            try {
                JSONObject jsonObject=new JSONObject(s);
                String status=jsonObject.getString("status");
                String articles=jsonObject.getString("articles");
                String totalResults=jsonObject.getString("totalResults");

                Log.i("result status",status);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
