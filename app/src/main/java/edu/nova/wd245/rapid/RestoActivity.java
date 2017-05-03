package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RestoActivity extends AppCompatActivity {

    Restaurant resto;
    ArrayList<Restaurant> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto);


        Intent i = getIntent();
        resto = (Restaurant) i.getSerializableExtra("selectedCity");


        results = new ArrayList<Restaurant>();
        results.add(resto);
        updateDisplay();


    }

    public void updateDisplay() {
        RestoAdapter adapter = new RestoAdapter(this, results);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.restoListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Log.d("DEBUG", "clicked " + Integer.toString(position));

                Intent intent = new Intent(RestoActivity.this, MenuActivity.class);
                intent.putExtra("selectedCity", results.get(position));

                startActivity(intent);
            }
        });

    }



    public void showMenu(View view) {
        Intent i = new Intent(RestoActivity.this, MenuActivity.class);
        i.putExtra("selectedResto",resto);
        startActivity(i);
    }

    public void goBack(View view) {
        Intent intent = new Intent(RestoActivity.this, CityListActivity.class);
        startActivity(intent);
    }
}