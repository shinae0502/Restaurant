package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;


import com.google.android.gms.common.util.Strings;
import com.study.restaurant.BR;
import com.study.restaurant.util.LOG;

import java.util.ArrayList;

public class Cities extends BaseObservable implements Cloneable {

    ArrayList<City> cities = new ArrayList<>();
    Region lastSelectRegion = new Region();
    FindRestaurant parent;

    public Cities clone() throws CloneNotSupportedException {
        Cities cities = new Cities();
        cities.setCities(new ArrayList<>(this.cities.size()));
        for (City city : this.cities) {
            cities.addCity(city.clone());
        }

        return cities;
    }

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
                    city.addRegion(region);
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
        releaseAllSelected();
    }

    public void releaseAllSelected() {
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
        if (count > 1)
            return lastSelectRegion.region_name + " 외 " + (count - 1) + "곳";
        else
            return lastSelectRegion.region_name;
    }

    @Override
    public String toString() {
        String str = "";
        for (City city : cities) {
            str += city.toString() + "\n";
        }
        return str;
    }

    public String getSelectedRegionIds() {
        String regionIds = "";

        for (City city : cities) {
            if (Integer.valueOf(city.getSelectedRegionCount()) > 0) {
                regionIds += city.getSelectedRegionIds();
            }
        }
        if (regionIds.length() > 0)
            regionIds = regionIds.substring(0, regionIds.length() - 1);
        LOG.d(regionIds);
        return regionIds;
    }
}
