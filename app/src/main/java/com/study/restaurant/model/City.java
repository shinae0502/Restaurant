package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.study.restaurant.BR;

import java.util.ArrayList;

public class City extends BaseObservable{

    String city_id;
    String city_name;

    @Bindable
    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    @Bindable
    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public static City build()
    {
        return new City();
    }

    public City city_id(String city_id)
    {
        this.city_id = city_id;
        return this;
    }

    public City city_name(String city_name)
    {
        this.city_name = city_name;
        return this;
    }

    //도시는 지역을 갖고 있다.
    ArrayList<Region> regions = new ArrayList<>();

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    public void refreshCount()
    {
        notifyPropertyChanged(BR.selectedRegionCount);
        notifyPropertyChanged(BR.visibleCount);
    }


    //지역은 선택 될 수 있고 선택된 카운트를 가져 올 수 있어야한다.
    @Bindable
    public String getSelectedRegionCount()
    {
        int count = 0;

        if(regions != null)
        {
            for(Region region : regions)
            {
                if(region.isSelected)
                    count++;
            }
        }

        return ""+count;
    }

    @Bindable
    public boolean getVisibleCount()
    {
        if(Integer.valueOf(getSelectedRegionCount()) > 0 )
            return true;
        else
            return false;
    }
}
