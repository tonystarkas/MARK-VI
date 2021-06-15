package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.myapplication.objects.Kategorijos;
import com.example.myapplication.rest.restControl;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.*;
public class CatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        getCategories getCat = new getCategories();
        getCat.execute();
    }

    public void returnCategories(View v){
        getCategories getCat = new getCategories();
        getCat.execute();
        Toast.makeText(CatActivity.this, "Atnaujinta!", Toast.LENGTH_SHORT).show();
    }

    private final class getCategories extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String output = "";
            String url = "http://10.0.2.2:8080/LaboratorinisDu_war_exploded/categories/categoryList";
            try {
                return restControl.sendGet(url);
            } catch (Exception e) {
                e.printStackTrace();
                return "Klaida";
            }
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);
            System.out.println("RECEIVED:" + results);
            if (results != null) {
                try {
                    Type listType = new TypeToken<ArrayList<Kategorijos>>() {
                    }.getType();
                    final List<Kategorijos> catData = new Gson().fromJson(results, listType);
                    ListView listview = findViewById(R.id.categoryList);
                    ArrayAdapter<Kategorijos> arrayAdapter = new ArrayAdapter<>(CatActivity.this, R.layout.list, catData);
                    listview.setAdapter(arrayAdapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent finance = new Intent(CatActivity.this, FinancesActivity.class);
                            finance.putExtra("selected",(Kategorijos) catData.get(position));
                            startActivity(finance);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CatActivity.this, "Klaida", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(CatActivity.this, "Klaida", Toast.LENGTH_LONG);
            }

        }
    }
}