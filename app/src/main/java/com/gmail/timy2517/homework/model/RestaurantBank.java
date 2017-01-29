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

    public void printBank() {
        System.out.println("------------------------------------------");
        System.out.println(mRestaurantList.get(0).getAddress());
        System.out.println(mRestaurantList.get(0).getId());
        System.out.println(mRestaurantList.get(0).getPhones());

        System.out.println("------------------------------------------");
        System.out.println(mRestaurantList.get(1).getAddress());
        System.out.println(mRestaurantList.get(1).getId());
        System.out.println(mRestaurantList.get(1).getPhones());

        System.out.println("------------------------------------------");
        System.out.println(mRestaurantList.get(2).getAddress());
        System.out.println(mRestaurantList.get(2).getId());
        System.out.println(mRestaurantList.get(2).getPhones());
    }
}
