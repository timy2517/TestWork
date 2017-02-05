package com.gmail.timy2517.homework.downloadParse;

import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Artem Novik on 26.10.2016.
 */

public class FoodDownloader extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private static final String URL = "http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN";

    public FoodDownloader(Context context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... urls) {
        try {
            loadCategories(mContext);
            loadRestaurantList(mContext);
            loadXmlFromNetwork(URL);
        } catch (IOException | XmlPullParserException e) {
            e.getMessage();
        }
        return null;
    }

    private void loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "MyAppName/1.0");

        FoodParser mFoodParser = new FoodParser();
        mFoodParser.parse(conn.getInputStream());
    }

    private void loadCategories(Context context) throws IOException, XmlPullParserException {

        CategoryParser mParser = new CategoryParser();
        mParser.parseXml(context);
    }

    private void loadRestaurantList(Context context) throws IOException, XmlPullParserException {
        RestaurantsListParser mParser = new RestaurantsListParser();
        mParser.parseXml(context);
    }
}






























