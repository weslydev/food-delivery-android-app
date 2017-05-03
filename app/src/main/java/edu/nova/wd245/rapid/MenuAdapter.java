package edu.nova.wd245.rapid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jwes on 3/20/17.
 */


public class MenuAdapter  extends ArrayAdapter<Restaurant> {

    public MenuAdapter(Context context, ArrayList<Restaurant> results) {
        super(context, 0, results);
    }

    /*
    (Context context, ArrayList<Restaurant> results) {
        super(context, 0, results);

    }
     */



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder;
        final Restaurant resto = getItem(position);
        final ArrayList<String> items = new ArrayList<String>();
        final ArrayList<String> arrayPrice = new ArrayList<String>();


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_list, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.itemTextView = (TextView) convertView.findViewById(R.id.itemTextView);
            viewHolder.priceTextView = (TextView) convertView.findViewById(R.id.pricetTextView);
            viewHolder.restoNameTextView = (TextView) convertView.findViewById(R.id.restoNameTextView2);
            viewHolder.button2 = (Button) convertView.findViewById(R.id.button2);


            //    viewHolder.restoImageView = (ImageView) convertView.findViewById(R.id.restoImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.itemTextView.setText(resto.getItem());
        viewHolder.priceTextView.setText("$" + resto.getPrice());
        viewHolder.restoNameTextView.setText(resto.getName() + " " + "Menu");

        viewHolder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = resto.item;
                String price = resto.price;
                String totalPrice;
                String secondItems;
                Integer occurrenceOfItem =0;
                items.add(item);
                arrayPrice.add(price);
                //items.add(price);



                //-------------------------------------------

                // Get the total cost

                Integer [] Array = new Integer[arrayPrice.size()];

                int sum = 0;

                for (int i = 0; i < Array.length; i++){

                    sum = sum + Integer.parseInt(String.valueOf(arrayPrice.get(i)));

//                    if (i%2!=0){
//                        sum = sum +  Integer.parseInt(String.valueOf(arrayPrice.get(i)));
//                    }

                    Log.d("SUM", String.valueOf(sum));

                    //occurrenceOfItem = (i+i)/2;
                    // Log.d("Occurence of element", String.valueOf(occurrenceOfItem));

                }




                //Add values to SharedPref



                SharedPreferences myPref = getContext().getSharedPreferences("PREF",getContext().MODE_PRIVATE);
                SharedPreferences myPref2 = getContext().getSharedPreferences("TOTAL",getContext().MODE_PRIVATE);
                SharedPreferences myPref3 = getContext().getSharedPreferences("PRICE",getContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = myPref.edit();
                SharedPreferences.Editor editor1 = myPref2.edit();
                SharedPreferences.Editor editor2 = myPref3.edit();
                editor.putString("ITEMS",items.toString());
                editor1.putString("SUM",String.valueOf(sum));
                editor2.putString("ITEM_PRICE", arrayPrice.toString());


                editor.commit();
                editor1.commit();
                editor2.commit();


                Log.d("DEBUG", items.toString());
                Log.d("DEBUG PRICE", arrayPrice.toString());
                Toast.makeText(getContext(),"Item added to your cart", Toast.LENGTH_SHORT).show();

            }

        });




        return convertView;
    }

    class ViewHolder {
        TextView itemTextView;
        TextView priceTextView;
        TextView restoNameTextView;
        Button button2;

    }
}
