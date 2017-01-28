package com.gmail.timy2517.homework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Novik on 28.10.2016.
 */
public class FoodBank {

    private  static FoodBank instance = new FoodBank();

    private boolean mFoodBankReady = false;
    private List<Food> mFoodList;

    private FoodBank(){
        mFoodList = new ArrayList<>();
    }

    public static FoodBank getInstance(){
        return instance;
    }

    public boolean isFoodBankReady() {
        return mFoodBankReady;
    }

    public void setFoodBankReady(boolean foodBankReady) {
        mFoodBankReady = foodBankReady;
    }

    public void addFood(Food food){
        mFoodList.add(food);
    }

    public List getFoodList(){
        return mFoodList;
    }

    public Food getFood(int foodId){
        return mFoodList.get(foodId);
    }

    public List getFoodListByCategoryId(int categoryId){
        List<Food> foodList = new ArrayList<>();

        for (int i = 0; i < mFoodList.size(); i++){
            if (mFoodList.get(i).getCategoryId() == categoryId){
                foodList.add(mFoodList.get(i));
            }
        }
        return foodList;
    }
}
