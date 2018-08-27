package com.study.restaurant.activity;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.study.restaurant.model.FindRestaurant;

public class GlobalApplication extends Application {

    FindRestaurant findRestaurant;

    public FindRestaurant getFindRestaurant() {
        if(findRestaurant == null)
            findRestaurant = new FindRestaurant();
        return findRestaurant;
    }

    public void setFindRestaurant(FindRestaurant findRestaurant) {
        this.findRestaurant = findRestaurant;
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /*Cities cities;
    Sort sort;
    Boundary boundary;
    Filter filter;

    public Filter getFilter() {
        if (filter == null)
            filter = new Filter();
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

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
    }*/
}
