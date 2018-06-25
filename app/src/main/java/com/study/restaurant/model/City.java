package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;

import com.study.restaurant.BR;

import java.util.ArrayList;

public class City extends BaseObservable implements Cloneable {

    Cities parent;
    String city_id;
    String city_name;
    //도시는 지역을 갖고 있다.
    ArrayList<Region> regions = new ArrayList<>();

    @Override
    public String toString() {
        String str = "";
        str += "city_id:" + city_id + "\n";
        str += "city_name:" + city_name + "\n";
        for (Region region : regions) {
            str += region.toString() + "\n";
        }
        return str;
    }

    public City clone() throws CloneNotSupportedException {
        City city = new City();
        city.setCity_name(city_name);
        city.setCity_id(city_id);
        for (Region region : regions) {
            city.addRegion(region.clone());
        }
        return city;
    }

    public Cities getParent() {
        return parent;
    }

    public void setParent(Cities parent) {
        this.parent = parent;
    }

    @Bindable
    public String getCity_id() {
        return city_id;
    }

    @Bindable
    public String getCity_name() {
        return city_name;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public void refreshCount() {
        //숫자표시
        notifyPropertyChanged(BR.selectedRegionCount);
        //0일경우 사라지게함
        notifyPropertyChanged(BR.visibleCount);
    }


    //지역은 선택 될 수 있고 선택된 카운트를 가져 올 수 있어야한다.
    @Bindable
    public String getSelectedRegionCount() {
        int count = 0;

        if (regions != null) {
            for (Region region : regions) {
                if (region.isChecked)
                    count++;
            }
        }

        return "" + count;
    }

    @Bindable
    public boolean getVisibleCount() {
        if (Integer.valueOf(getSelectedRegionCount()) > 0)
            return true;
        else
            return false;
    }

    public void addRegion(Region region) {
        region.setParent(this);
        regions.add(region);
    }

    public static class Builder {

        String city_id;
        String city_name;

        public Builder setCity_id(String city_id) {
            this.city_id = city_id;
            return this;
        }

        public Builder setCity_name(String city_name) {
            this.city_name = city_name;
            return this;
        }

        public City create() {
            City city = new City();
            city.setCity_name(city_name);
            city.setCity_id(city_id);
            return city;
        }

    }

}
