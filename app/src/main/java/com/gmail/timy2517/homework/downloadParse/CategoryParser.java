package com.gmail.timy2517.homework.downloadParse;

import android.content.Context;

import com.gmail.timy2517.homework.R;
import com.gmail.timy2517.homework.model.Category;
import com.gmail.timy2517.homework.model.CategoryBank;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Artem Novik on 14.11.2016.
 */
class CategoryParser {

    private CategoryBank mCategoryBank = CategoryBank.getInstance();

    public void parseXml(Context context) throws XmlPullParserException, IOException {

        XmlPullParser parser = context.getResources().getXml(R.xml.categories);

        while (true){
            switch (parser.getEventType()){
                case XmlPullParser.END_DOCUMENT:
                    return;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("category")){
                        Category category = new Category();
                        category.setName(parser.getAttributeValue(null, "name"));
                        category.setCategoryId(Integer.valueOf(parser.getAttributeValue(null, "id")));
                        int iconId = context.getResources()
                                .getIdentifier(parser.getAttributeValue(null, "icon"), "drawable", context.getPackageName());
                        category.setCategoryIconId(iconId);
                        mCategoryBank.addCategory(category);
                    }
                    break;
                default:
                    break;
            }
            parser.next();
        }


    }
}
