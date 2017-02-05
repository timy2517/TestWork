package com.gmail.timy2517.homework.downloadParse;

import com.gmail.timy2517.homework.model.Food;
import com.gmail.timy2517.homework.model.FoodBank;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Artem Novik on 28.10.2016.
 */
class FoodParser {

    private FoodBank mFoodBank = FoodBank.getInstance();

    public void parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();
            parser.setInput(in, null);
            readFeed(parser);
        } finally {
            in.close();
        }
    }

    private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG) {


                if (parser.getName().equals("offer")) {
                    mFoodBank.addFood(readFood(parser));
                }
            } else if (parser.getEventType() == XmlPullParser.END_TAG && parser.getName().equals("yml_catalog")) {
                mFoodBank.setFoodBankReady(true);
            }
            parser.next();
        }
    }

    private Food readFood(XmlPullParser parser) throws XmlPullParserException, IOException {

        Food mFood = new Food();
        String name;
        String text = "";
        while (true) {

            if (parser.getEventType() == XmlPullParser.TEXT) {
                text = parser.getText();
            } else if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (parser.getName().equals("param") && parser.getAttributeValue(null, "name").equals("Вес")) {
                    parser.next();
                    text = parser.getText();
                    mFood.setWeight(text);
                }
            } else if (parser.getEventType() == XmlPullParser.END_TAG) {
                if (parser.getName().equals("offer")) {
                    break;
                } else {
                    name = parser.getName();

                    switch (name) {
                        case "name":
                            mFood.setName(text);
                            break;
                        case "price":
                            mFood.setPrice(text);
                            break;
                        case "description":
                            mFood.setDescription(text);
                            break;
                        case "categoryId":
                            mFood.setCategoryId(Integer.valueOf(text));
                            break;
                        case "picture":
                            mFood.setImageUrl(text);
                            break;
                    }
                }

            }

            parser.next();
        }

        mFood.setId(mFoodBank.getFoodList().size());
        return mFood;
    }
}
