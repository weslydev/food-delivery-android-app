package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jwes on 3/21/17.
 */

public class MenuActivity extends AppCompatActivity {

    Restaurant resto;

    ArrayList<Restaurant> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent i = getIntent();
        resto = (Restaurant) i.getSerializableExtra("selectedResto") ;

        results = new ArrayList<>();
        results.add(resto);
        updateDisplay();


        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setIcon(R.drawable.shoppincart);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_history){
            Toast.makeText(this,"Order History", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MenuActivity.this, CartActivity.class);
            //i.putExtra("theOrders",orders);
            startActivity(i);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void updateDisplay() {
        MenuAdapter adapter = new MenuAdapter(this, results);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.menuListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Log.d("DEBUG", "clicked " + Integer.toString(position));



                Intent intent = new Intent(MenuActivity.this, CartActivity.class);
                intent.putExtra("selectedMenu", results.get(position));


                startActivity(intent);
            }
        });

    }


}
