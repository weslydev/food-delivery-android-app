package edu.nova.wd245.rapid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jwes on 3/3/17.
 */

public class CityAdapter extends ArrayAdapter<Restaurant> {

    public CityAdapter(Context context, ArrayList<Restaurant> results) {
        super(context, 0, results);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get Total Price from sharedPreferences


        // Get the data item for this position
         ViewHolder viewHolder;
        Restaurant resto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.city_name_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.cityNameTextView = (TextView) convertView.findViewById(R.id.cityTextView);
            viewHolder.stateTextView = (TextView) convertView.findViewById(R.id.stateTextView);
        //    viewHolder.restoImageView = (ImageView) convertView.findViewById(R.id.restoImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.cityNameTextView.setText(resto.getLocation());
        viewHolder.stateTextView.setText(resto.getState());

//        if (viewHolder.restoImageView != null) {// download the image in the background
//            ImageDownloader task = new ImageDownloader(viewHolder.restoImageView);
//            task.execute("https://api.backendless.com/5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00/v1/files/media/" + resto.getShortName() + ".jpg");
//        }
        return convertView;
    }

    class ViewHolder {
        TextView cityNameTextView;
        TextView stateTextView;
     //   ImageView restoImageView;
    }
}


