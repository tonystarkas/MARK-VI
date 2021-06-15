package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.myapplication.rest.restControl;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connection(View v) throws NoSuchAlgorithmException
    {
        EditText eName = findViewById(R.id.loginField);
        EditText ePassword = findViewById(R.id.passField);
        String request = "{\"vardas\":\"" + eName.getText().toString() + "\", \"slaptazodis\":\"" + ePassword.getText().toString() + "\"}";
        userLogin userlogin = new userLogin();
        userlogin.execute(request);
    }

    public final class userLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Validating data", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String url = "http://10.0.2.2:8080/LaboratorinisDu_war_exploded/user/login";
            String postDataParameters = params[0];
            System.out.println("SENT" + postDataParameters);
            try {
                return restControl.sendPost(url, postDataParameters);
            } catch (Exception e) {
                e.printStackTrace();
                return "Klaida";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            System.out.println("Received" + result);
            if (result.equals("Sekmingai prisijungta")) {
                try {
                    Intent Meniu = new Intent(MainActivity.this, MeniuActivity.class);
                    startActivity(Meniu);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Neteisingi duomenys", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}