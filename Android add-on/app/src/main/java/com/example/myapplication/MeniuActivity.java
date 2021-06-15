package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MeniuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu);
    }
    public void openCategory(View v)
    {
        Intent Category = new Intent(MeniuActivity.this, CatActivity.class);
        startActivity(Category);
    }
    public void logout(View v)
    {
        Intent logout = new Intent(MeniuActivity.this, MainActivity.class);
        startActivity(logout);
        Toast.makeText(MeniuActivity.this, "Bye!", Toast.LENGTH_SHORT).show();
    }
}