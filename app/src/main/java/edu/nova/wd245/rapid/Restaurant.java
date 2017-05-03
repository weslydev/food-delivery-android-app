package edu.nova.wd245.rapid;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Jwes on 3/4/17.
 */

public class Restaurant implements Serializable {

    public static String ID_KEY = "id";
    public static String NAME_KEY = "name";
    public static String LOCATION_KEY = "location";
    public static String STATE_KEY = "state";
    public static String SHORTNAME_KEY = "shortName";
    public static String IMAGE_KEY = "imageLink";

    public static String ITEM_KEY = "item";
    public static String PRICE_KEY = "price";



    String id;
    String name;
    String location;
    String state;
    String shortName;
    String imageLink;


    String item;
    String price;


    public Restaurant(String id, String name, String location, String state,  String shortName, String imageLink, String item, String price) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.state = state;
        this.shortName = shortName;
        this.imageLink = imageLink;
        this.item = item;
        this.price = price;

    }

    public Restaurant(JSONObject jo) {
        try {
            id = jo.getString(ID_KEY);
            name = jo.getString(NAME_KEY);
            shortName = jo.getString(SHORTNAME_KEY);
            location = jo.getString(LOCATION_KEY);
            state = jo.getString(STATE_KEY);
            imageLink = jo.getString(IMAGE_KEY);
            item = jo.getString(ITEM_KEY);
            price = jo.getString(PRICE_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", state='" + state + '\'' +
                ", shortName='" + shortName + '\'' +
                ", item='" + item + '\'' +
                ", price='" + price + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
