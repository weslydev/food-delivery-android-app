package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class ThankActivity extends AppCompatActivity {

    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);

        textField = (EditText) findViewById(R.id.textfieldEditText);
        textField.setEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //noinspection SimplifiableIfStatement
        if (id == R.id.logout){

            Backendless.UserService.logout(new AsyncCallback<Void>() {
                @Override
                public void handleResponse(Void aVoid) {

                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {

                }
            });

            Intent i = new Intent(this, LoginActivity.class);
            //i.putExtra("theOrders",orders);
            startActivity(i);

            return true;
        }
        if (id == R.id.about) {
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void GoToNewOrder(View view) {

        Intent intent = new Intent(this, CityListActivity.class);
        startActivity(intent);
    }
}
