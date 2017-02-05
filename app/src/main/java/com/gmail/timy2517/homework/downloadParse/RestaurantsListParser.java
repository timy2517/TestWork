package com.gmail.timy2517.homework.downloadParse;

import android.content.Context;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.model.Restaurant;
import com.gmail.timy2517.homework.model.RestaurantBank;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by artem on 25.1.17.
 */

public class RestaurantsListParser {

    private RestaurantBank mRestaurantBank = RestaurantBank.getInstance();

    public void parseXml(Context context) throws XmlPullParserException, IOException {

        XmlPullParser parser = context.getResources().getXml(R.xml.restaurants);

        while (true) {
            switch (parser.getEventType()) {
                case XmlPullParser.END_DOCUMENT:
                    return;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("restaurant")) {

                        mRestaurantBank.addRestaurant(reedRestaurant(parser));

                    }
                    break;
                default:
                    break;
            }
            parser.next();
        }
    }

    private Restaurant reedRestaurant(XmlPullParser parser) throws IOException, XmlPullParserException {
        Restaurant mRestaurant = new Restaurant();

        String text = "";

        while (true) {
            if (parser.getEventType() == XmlPullParser.TEXT) {
                text = parser.getText();
            } else if (parser.getEventType() == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case "address":
                        mRestaurant.setAddress(text);
                        break;
                    case "id":
                        mRestaurant.setId(Integer.valueOf(text));
                        break;
                    case "phone":
                        mRestaurant.getPhones().add(text);
                        break;
                    case "restaurant":
                        return mRestaurant;
                }

            }
            parser.next();
        }


    }
}
