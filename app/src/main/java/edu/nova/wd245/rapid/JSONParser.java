package edu.nova.wd245.rapid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mitropoulis on 10/16/16.
 * Simple JSONParser class that downloads a JSON feed from a remote URL and converts it to a JSONObject
 * which will be processed further elsewhere.
 * The static method should be executed an a background thread
 */


public class JSONParser {

    public static JSONObject getJSONFeed(String jsonURL) {

        BufferedReader reader = null;
        JSONObject json = null;

        try {
            URL url = new URL(jsonURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            json = new JSONObject(sb.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return json;
        }

    }

}
