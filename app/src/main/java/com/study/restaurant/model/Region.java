package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.study.restaurant.BR;

public class Region extends BaseObservable {
    String region_id;
    String region_name;
    String city_id;
    City parent;
    boolean isChecked = false;

    public City getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Bindable
    public boolean getChecked()
    {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        //마지막 선택한 지역 등록
        if(checked)
        {
            parent.parent.setLastSelectRegion(this);
        }
        parent.refreshCount();
        parent.parent.validate();
        notifyPropertyChanged(BR.checked);
    }


    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }


    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }


    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
