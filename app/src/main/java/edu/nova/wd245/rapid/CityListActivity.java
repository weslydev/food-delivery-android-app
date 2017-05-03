package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityListActivity extends AppCompatActivity {

    ArrayList<Restaurant> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        results = new ArrayList<Restaurant>();
        JSONParseTask task = new JSONParseTask();
        task.execute("https://api.backendless.com/5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00/v1/files/files/json3.json");

    }

    public void updateDisplay() {
        CityAdapter adapter = new CityAdapter(this, results);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.cityListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Log.d("DEBUG", "clicked " + Integer.toString(position));

                Intent intent = new Intent(CityListActivity.this, RestoActivity.class);
                intent.putExtra("selectedCity", results.get(position));

                startActivity(intent);
            }
        });




    }
    private class JSONParseTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            // Getting JSON from URL
            JSONObject json = JSONParser.getJSONFeed(args[0]);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            try {



                JSONObject jsonObject = json.getJSONObject("results");
                JSONArray jsonArray = jsonObject.getJSONArray("restaurant");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject restoJSON = jsonArray.getJSONObject(i);

                    Restaurant resto = new Restaurant(restoJSON);

                    JSONArray jsonArray1 = restoJSON.getJSONArray("menu");
                    for(int j = 0; j < jsonArray1.length(); j++ ){
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);

                         resto.setItem(jsonObject1.getString("item"));
                         resto.setPrice(jsonObject1.getString("price"));

                    }
                 //   Restaurant resto = new Restaurant(id,name,location,state,shortname,imageLink,price,item);
                    results.add(resto);
                    Log.d("DEBUG FROM RESTO", results.toString());
                }
                updateDisplay();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
