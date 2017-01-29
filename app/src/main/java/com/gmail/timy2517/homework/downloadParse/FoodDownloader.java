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
    //ProgressDialog mProgressDialog;

    public FoodDownloader(Context context) {
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... urls) {
        try {
            loadCategories(mContext);
            loadRestaurantList(mContext);
            loadXmlFromNetwork("http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN");
        } catch (IOException e) {
            e.getMessage();
        } catch (XmlPullParserException e) {
            e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //mProgressDialog = new ProgressDialog(mContext);
        //mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //mProgressDialog.setMessage(mContext.getString(R.string.progress_dialog));
        //mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //mProgressDialog.cancel();
    }

    private void loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent", "MyAppName/1.0");

        FoodParser mFoodParser = new FoodParser();
        mFoodParser.parse(conn.getInputStream());
    }

    public void loadCategories(Context context) throws IOException, XmlPullParserException {

        CategoryParser mParser = new CategoryParser();
        mParser.parseXml(context);
    }

    public void loadRestaurantList(Context context) throws IOException, XmlPullParserException {
        RestaurantsListParser mParser = new RestaurantsListParser();
        mParser.parseXml(context);
    }
}






























