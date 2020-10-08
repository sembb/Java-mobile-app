package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText editEmail, editPassword, editName;
    private Object HttpsURLConnection;
    URL url;
    TextView testtekst;
    JSONObject json;
    OkHttpClient client = new OkHttpClient();



    {
        try {
            url = new URL ("https://hondenschoolzuidwestfriesland.nl/mobindex.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.btnSignIn);
        editName = (EditText)findViewById(R.id.editTextTextPersonName);
        editPassword = (EditText)findViewById(R.id.editTextTextPassword);
        testtekst = findViewById(R.id.testtekst);

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://reqres.in/api/users/2").newBuilder();
        String url = urlBuilder.build().toString();

        final Request request = new Request.Builder()
                .url(url)
                .build();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String myResponse = response.body().string();

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    JSONObject json = new JSONObject(myResponse);

                                    testtekst.setText(json.getJSONObject("data").getString("first_name")+ " "+json.getJSONObject("data").getString("last_name"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });
            }
        });
    }


    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {


            String email = args[2];
            String password = args[1];
            String name = args[0];

            ContentValues params=new ContentValues();
            params.put("username", name);
            params.put("password", password);
            if (email.length() > 0) {
                params.put("email", email);
            }






            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(), result.getString("message"), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }



}