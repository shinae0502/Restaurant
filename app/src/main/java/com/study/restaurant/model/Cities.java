package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;


import com.study.restaurant.BR;

import java.util.ArrayList;

public class Cities extends BaseObservable {

    ArrayList<City> cities;
    Region lastSelectRegion = new Region();

    public Region getLastSelectRegion() {
        return lastSelectRegion;
    }

    public void setLastSelectRegion(Region lastSelectRegion) {
        this.lastSelectRegion = lastSelectRegion;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void addCity(int index, City city) {
        city.setParent(this);
        cities.add(index, city);
    }

    public void addCity(City city) {
        city.setParent(this);
        cities.add(city);
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
        for (City city : cities)
            city.setParent(this);
    }

    public City getCity(String cityName) {
        for (City city : cities) {
            if (city.city_name.equals(cityName))
                return city;
        }
        return new City();
    }


    public void setRegions(ArrayList<Region> regions) {
        for (City city : cities) {
            for (Region region : regions) {
                if (city.city_id.equals(region.city_id)) {
                    region.setParent(city);
                    city.regions.add(region);
                }
            }
        }
    }

    @Bindable
    public boolean getIsSelectedRegion() {
        for (City city : cities) {
            for (Region region : city.regions) {
                if (region.isChecked == true)
                    return true;
            }
        }
        return false;
    }

    public void validate() {
        notifyPropertyChanged(BR.isSelectedRegion);
    }

    public void releaseAllSelected(View v) {
        for (City city : cities) {
            for (Region region : city.regions) {
                region.setChecked(false);
            }
        }
        validate();
    }

    @Bindable
    public String getSelectedCities() {
        int count = 0;
        for (City city : cities) {
            count += Integer.valueOf(city.getSelectedRegionCount());
        }
        if (count > 0)
            return lastSelectRegion.region_name + " 외 " + (count-1) + "곳";
        else
            return lastSelectRegion.region_name;
    }
}
