package edu.nova.wd245.rapid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    Restaurant resto;

    ArrayList<String> results;
    ArrayList<String> resultsPrice;
    ListView listView;


    public static final int PAYPAL_REQUEST_CODE = 123;

    Double total;
    TextView totalPrice;
    TextView shippingCost;
    TextView TotalAmount;
    String totalAmt;


    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_item_table);

         //Paypal Service


        Intent intent = new Intent(this, PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);



        //Get SharedP items from previous Activity
         totalPrice = (TextView) findViewById(R.id.orderPriceTextView);
        TextView numberItem = (TextView) findViewById(R.id.numberItemTextView);
         shippingCost = (TextView) findViewById(R.id.shippingPriceTextView);
         TotalAmount = (TextView) findViewById(R.id.amountPriceTextView);

        SharedPreferences sharedPreferences = getSharedPreferences("PREF", 0);
        String items = sharedPreferences.getString("ITEMS", "");
        String myItems = items.replace("[","").replace("]","");

        Log.d("myITEM", myItems);

        SharedPreferences sharedPreferences1 = getSharedPreferences("TOTAL",0);
        String sum = sharedPreferences1.getString("SUM","");
        //String total = String.valueOf(sum);

        SharedPreferences sharedPreferences2 = getSharedPreferences("PRICE", 0);
        String price = sharedPreferences2.getString("ITEM_PRICE", "");
        String myPrices = price.replace("[","").replace("]","");
       // myPrices =  myPrices;
        String dollardSign = "$";
        String shipping = "5";
        //Log.d("DEBUGGGGG",String.valueOf(sum));

        total = Double.valueOf(sum);
        total = total +5.00;
        totalAmt = String.valueOf(total);


          totalPrice.setText("Order total cost:   $"+ Double.valueOf(sum));
          shippingCost.setText("Shipping:                  $"+ Double.valueOf(shipping));
          TotalAmount.setText("Total Amount:      $"+totalAmt);


        results = new ArrayList<String>();
        resultsPrice =new ArrayList<>();
        results.add(myItems);
        //results.add(price);
        resultsPrice.add(myPrices);


        int number = results.size();

        Log.d("NUMBERRRRRR", String.valueOf(number));
        Log.d("ITEM", myItems);
        Log.d("RESULTS", results.toString());
        Log.d("PRICESS", resultsPrice.toString());





        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);


        ////row 2


        for(int i=0;i<results.size();i++)
        {
            TableRow row=new TableRow(this);
            String item = results.get(i).replace(",","                  \n");
            Log.d("item",item.replace(",","\n"));
            String itemPrice = resultsPrice.get(i).replace(",","\n");
            Log.d("itemPrice", itemPrice);
            TextView itemTextView= new TextView(this);
            itemTextView.setText(item);
            itemTextView.setTextColor(Color.parseColor("#0000FF"));
            row.addView(itemTextView);


            TextView itemPriceTextView = new TextView(this);
            itemPriceTextView.setText(itemPrice);
            itemPriceTextView.setTextColor(Color.parseColor("#0000FF"));
            row.addView(itemPriceTextView);
            tableLayout.addView(row);
        }



    }
    private void getPayment() {
        //Getting the amount from editText
       // paymentAmount = editTextAmount.getText().toString();


        //Creating a PayPalpayment


        PayPalPayment payment = new PayPalPayment( new BigDecimal(totalAmt), "USD", "Rapid",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", totalAmt ));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }


    public void payNow(View view) {
        getPayment();

    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
