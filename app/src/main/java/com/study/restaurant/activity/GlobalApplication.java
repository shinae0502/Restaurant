package com.study.restaurant.activity;

import android.app.Application;

import com.study.restaurant.model.Boundary;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Sort;

public class GlobalApplication extends Application {

    Cities cities;
    Sort sort;
    Boundary boundary;

    public Boundary getBoundary() {
        if (boundary == null)
            boundary = new Boundary();
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

    public Sort getSort() {
        if (sort == null)
            sort = new Sort();
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }
}
