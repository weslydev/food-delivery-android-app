package edu.nova.wd245.rapid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmationActivity extends AppCompatActivity {

    public final String APP_ID = "5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00";
    public final String SECRET_KEY = "0FF67760-9E68-DC4C-FF3C-A1D292835900";
    public final String VERSION = "v1";

    EditText addr;
    EditText phone;
    BackendlessUser loggedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Backendless.initApp(this, APP_ID,SECRET_KEY,VERSION);
        //Getting Intent
        Intent intent = getIntent();

//        // BackendlessUser loggedUser;
        loggedUser = Backendless.UserService.CurrentUser();
        Log.d("LoggedUser", loggedUser.toString());

        addr = (EditText) findViewById(R.id.addressEditText);
        phone = (EditText) findViewById(R.id.phoneEditText);



        try {
            JSONObject jsonDetails = new JSONObject(intent.getStringExtra("PaymentDetails"));

            //Displaying payment details
            showDetails(jsonDetails.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

        if (id == R.id.about){
            Intent aboutIntent = new Intent(this, AboutActivity.class);
            startActivity(aboutIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void showDetails(JSONObject jsonDetails, String paymentAmount) throws JSONException {

      //  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String transactDate = dateFormat.format(date);

        //Views
        TextView textViewId = (TextView) findViewById(R.id.paymentId);
        TextView textViewStatus= (TextView) findViewById(R.id.paymentStatus);
        TextView textViewAmount = (TextView) findViewById(R.id.paymentAmount);
        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);

        //Showing the details from json object
        textViewId.setText(jsonDetails.getString("id"));
        textViewStatus.setText(jsonDetails.getString("state"));
        textViewAmount.setText(paymentAmount+" USD");
        dateTextView.setText(transactDate);

    }


    public void submitDelInfo(View view) {
        String deliveryAddr;
        String deliveryPhone;

        BackendlessUser backendlessUser = new BackendlessUser();
        backendlessUser = loggedUser;


        deliveryAddr = addr.getText().toString();
        deliveryPhone = phone.getText().toString();
        backendlessUser.setProperty("deliveryAdd",deliveryAddr);
        backendlessUser.setProperty("deliveryNumb",deliveryPhone);

        Backendless.UserService.update(loggedUser, new AsyncCallback<BackendlessUser>() {

            @Override
            public void handleResponse(BackendlessUser backendlessUser) {

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });

        Log.d("DeliveryAddr",deliveryAddr);
        Log.d("LoggedUser", loggedUser.toString());

        Intent intent = new Intent(this, ThankActivity.class);
        startActivity(intent);



    }
}
