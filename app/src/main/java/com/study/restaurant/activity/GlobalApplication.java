package com.study.restaurant.activity;

import android.app.Application;

import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;

public class GlobalApplication extends Application {

    Cities cities;

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }
}
