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
    private Restaurant mRestaurant;

    public void parseXml(Context context) throws XmlPullParserException, IOException {

        XmlPullParser parser = context.getResources().getXml(R.xml.restaurants);

        while (true){
            switch (parser.getEventType()){
                case XmlPullParser.END_DOCUMENT:
                    return;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("restaurant")){
                        mRestaurant = new Restaurant();
                        mRestaurant.setAddress(parser.getAttributeValue(0));
                        mRestaurant.setId(Integer.valueOf(parser.getAttributeValue(1)));
                        mRestaurant.addPhone(parser.getAttributeValue(2));
                        mRestaurantBank.addRestaurant(mRestaurant);
                    }
                    break;
                default:
                    break;
            }
            parser.next();
        }


    }
}
