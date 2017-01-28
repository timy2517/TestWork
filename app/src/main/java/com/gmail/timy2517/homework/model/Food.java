package com.gmail.timy2517.homework.model;


/**
 * Created by Artem Novik on 24.10.2016.
 */
public class Food {

    private int mId;
    private int mCategoryId;
    private String mName;
    private String mWeight;
    private String mPrice;
    private String mDescription;

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    private String mImageUrl;

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getWeight() {
        if (mWeight == null) {
            return " ";
        } else {
            return mWeight;
        }
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
