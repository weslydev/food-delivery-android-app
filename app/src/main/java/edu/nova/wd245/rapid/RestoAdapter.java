package edu.nova.wd245.rapid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jwes on 3/4/17.
 */

public class RestoAdapter extends ArrayAdapter<Restaurant> {

    public RestoAdapter(Context context, ArrayList<Restaurant> results) {
        super(context, 0, results);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ViewHolder viewHolder;
         Restaurant resto = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resto_name_list, parent, false);
            viewHolder= new ViewHolder();
            viewHolder.restoNameTextView = (TextView) convertView.findViewById(R.id.restoNameTextView);
            viewHolder.restoImageView = (ImageView) convertView.findViewById(R.id.restoImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.restoNameTextView.setText(resto.getName());


        // Download the image in a background thread if necessary
        if (viewHolder.restoImageView != null) {
            // download the image in the background
            ImageDownloader task = new ImageDownloader(viewHolder.restoImageView);
            task.execute("https://api.backendless.com/5CEF286A-8FD9-07F3-FFD1-0E3054DCEE00/v1/files/media/" + resto.getShortName() + ".jpg");
        }
        return convertView;
    }

    class ViewHolder {
        TextView restoNameTextView;
        ImageView restoImageView;
    }

}
