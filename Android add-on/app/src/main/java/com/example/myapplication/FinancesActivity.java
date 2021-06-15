package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.objects.Finansai;
import com.example.myapplication.objects.Kategorijos;
import com.example.myapplication.rest.restControl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FinancesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finances);
        Kategorijos selectCat = (Kategorijos) getIntent().getSerializableExtra("selected");
        getIncome getInc = new getIncome();
        getInc.execute(Integer.toString(selectCat.getId()));
    }

    public void returnIncome(View v){
        getIncome getInc = new getIncome();
        getInc.execute();
        Toast.makeText(FinancesActivity.this, "Atnaujinta!", Toast.LENGTH_SHORT).show();
    }

    private final class getIncome extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String output = "";
            String url = "http://10.0.2.2:8080/LaboratorinisDu_war_exploded/finances/showFinances?id=" + strings[0];
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
                    Type listType = new TypeToken<ArrayList<Finansai>>() {}.getType();
                    final List<Finansai> incData = new Gson().fromJson(results, listType);
                    ListView listview = findViewById(R.id.incomeList);
                    ArrayAdapter<Finansai> arrayAdapter = new ArrayAdapter<>(com.example.myapplication.FinancesActivity.this, R.layout.list, incData);
                    listview.setAdapter(arrayAdapter);
                    //listview.setOnItemClickListener(((parent, view, position, id) -> {Toast info = Toast.makeText(com.example.myapplication.FinancesActivity.this, "Selected Income: " + incData.toString(), Toast.LENGTH_LONG);
                    //info.show();
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent finance = new Intent(FinancesActivity.this, FinancesActivity.class);
                            finance.putExtra("selected",(Finansai) incData.get(position));
                            startActivity(finance);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(com.example.myapplication.FinancesActivity.this, "Klaida", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(com.example.myapplication.FinancesActivity.this, "Klaida", Toast.LENGTH_LONG);
            }

        }
    }
}