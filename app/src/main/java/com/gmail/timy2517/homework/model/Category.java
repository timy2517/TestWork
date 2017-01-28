package com.gmail.timy2517.homework.model;

/**
 * Created by Artem Novik on 10.11.2016.
 */
public class Category {

    private String mName;
    private int mCategoryId;
    private int mCategoryIconId;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public int getCategoryIconId() {
        return mCategoryIconId;
    }

    public void setCategoryIconId(int categoryIconId) {
        mCategoryIconId = categoryIconId;
    }
}