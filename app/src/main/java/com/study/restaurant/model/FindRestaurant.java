package com.study.restaurant.model;

import android.location.Location;

import java.util.ArrayList;

public class FindRestaurant {
    Cities cities;
    Sort sort;
    Boundary boundary;
    Filter filter;
    ArrayList<Store> storeArrayList = new ArrayList<>();
    private Location myLocation;

    public ArrayList<Store> getStoreArrayList() {
        return storeArrayList;
    }

    public void setStoreArrayList(ArrayList<Store> storeArrayList) {
        this.storeArrayList = storeArrayList;
    }

    public Filter getFilter() {
        if (filter == null)
            filter = new Filter();
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Boundary getBoundary() {
        if (boundary == null) {
            boundary = new Boundary();
            boundary.parent = this;
        }
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        boundary.parent = this;
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
        if (cities == null)
            cities = new Cities();

        return cities;
    }

    public void setCities(Cities cities) {
        cities.parent = this;

        //지역이 하나라도 선택되어있다면
        if (cities.getIsSelectedRegion())
            boundary.releaseAll();

        this.cities = cities;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    public void addAllStoreArrayList(ArrayList<Store> storeArrayList) {
        /** 내 위치 설정해주기 */
        if (myLocation != null)
            for (Store store : storeArrayList) {
                store.setMyLocation(myLocation);
            }
        getStoreArrayList().addAll(storeArrayList);
    }
}
