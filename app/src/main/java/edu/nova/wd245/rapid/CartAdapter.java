package edu.nova.wd245.rapid;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jwes on 3/25/17.
 */

public class CartAdapter extends ArrayAdapter<Restaurant> {



    public CartAdapter(Context context, ArrayList<Restaurant> results) {
        super(context, 0, results);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get Total Price from sharedPreferences

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("PREF",0);
        String item = sharedPreferences.getString("ITEM","");

        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences("SUM",0);
        String sum = sharedPreferences1.getString("SUM","");

        ArrayList<String> resto;
        resto = new ArrayList<>();
        resto.add(item);

        // Get the data item for this position
        CartAdapter.ViewHolder viewHolder;

       // Restaurant resto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item_list, parent, false);
            viewHolder = new CartAdapter.ViewHolder();
            viewHolder.itemTextView = (TextView) convertView.findViewById(R.id.itemTextView);
            viewHolder.priceTextView = (TextView) convertView.findViewById(R.id.pricetTextView);
            //    viewHolder.restoImageView = (ImageView) convertView.findViewById(R.id.restoImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CartAdapter.ViewHolder) convertView.getTag();
        }

        for (int i =0; i <resto.size(); i++){

            viewHolder.itemTextView.setText(resto.get(i*2));
        }

       // viewHolder.itemTextView.setText(resto(0));
        //viewHolder.priceTextView.setText(resto.getPrice());

//        if (viewHolder.restoImageView != null) {// download the image in the background
//            ImageDownloader task = new ImageDownloader(viewHolder.restoImageView);
//            task.execute("https://api.backendless.com/5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00/v1/files/media/" + resto.getShortName() + ".jpg");
//        }
        return convertView;
    }

    class ViewHolder {
        TextView itemTextView;
        TextView priceTextView;
        TextView destAddressTextView;

        //   ImageView restoImageView;
    }
}
