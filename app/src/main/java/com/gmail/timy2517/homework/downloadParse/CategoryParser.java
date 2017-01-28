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
public class CategoryParser {

    private CategoryBank mCategoryBank = CategoryBank.getInstance();
    private Category mCategory;

    public void parseXml(Context context) throws XmlPullParserException, IOException {

        XmlPullParser parser = context.getResources().getXml(R.xml.categories);

        while (true){
            switch (parser.getEventType()){
                case XmlPullParser.END_DOCUMENT:
                    return;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("category")){
                        mCategory = new Category();
                        mCategory.setName(parser.getAttributeValue(0));
                        mCategory.setCategoryId(Integer.valueOf(parser.getAttributeValue(2)));
                        int id = context.getResources()
                                .getIdentifier(parser.getAttributeValue(1), "drawable", context.getPackageName());
                        mCategory.setCategoryIconId(id);
                        mCategoryBank.addCategory(mCategory);
                    }
                    break;
                default:
                    break;
            }
            parser.next();
        }


    }
}
