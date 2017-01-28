package com.gmail.timy2517.homework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 25.1.17.
 */

public class Restaurant {

    private String mAddress;
    private int mId;
    private List<String> mPhones;

    public Restaurant(){
        mPhones = new ArrayList<>();
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public List<String> getPhones() {
        return mPhones;
    }

    public void addPhone(String phone) {
        this.mPhones.add(phone);
    }
}
