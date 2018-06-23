package com.study.restaurant.activity;

import android.app.Application;

import com.study.restaurant.model.City;

public class GlobalApplication extends Application {

    City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
