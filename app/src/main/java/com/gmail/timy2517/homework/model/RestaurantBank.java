package com.gmail.timy2517.homework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 25.1.17.
 */

public class RestaurantBank {

    private  static RestaurantBank instance = new RestaurantBank();
    private List<Restaurant> mRestaurantList;

    private RestaurantBank(){
        mRestaurantList = new ArrayList<>();
    }

    public static RestaurantBank getInstance(){
        return instance;
    }

    public List getRestaurantList(){
        return mRestaurantList;
    }

    public void addRestaurant(Restaurant restaurant){
        mRestaurantList.add(restaurant);
    }

    public Restaurant getRestaurant(int id){
        return mRestaurantList.get(id);
    }
}
