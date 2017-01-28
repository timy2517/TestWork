package com.gmail.timy2517.homework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Novik on 14.11.2016.
 */
public class CategoryBank {

    private  static CategoryBank instance = new CategoryBank();

    private List<Category> mCategoryList;

    private CategoryBank(){
        mCategoryList = new ArrayList<>();
    }

    public static CategoryBank getInstance(){
        return instance;
    }

    public List getCategoryList() {
        return mCategoryList;
    }

    public void addCategory(Category category){
        mCategoryList.add(category);
    }
}
