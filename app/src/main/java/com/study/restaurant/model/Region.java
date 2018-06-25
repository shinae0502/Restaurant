package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.study.restaurant.BR;

public class Region extends BaseObservable implements Cloneable {
    String region_id;
    String region_name;
    String city_id;
    City parent;
    boolean isChecked = false;

    @Override
    public String toString() {
        String str = "";
        str += "region_id:" + region_id + "\n";
        str += "region_name:" + region_name + "\n";
        str += "city_id:" + city_id + "\n";
        str += "isChecked:" + isChecked + "\n";
        str += "parent:" + parent.getCity_id() + "\n";
        str += "parent:" + parent.getCity_name();
        return str;
    }

    public Region clone() throws CloneNotSupportedException {
        return (Region) super.clone();
    }

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
    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        //마지막 선택한 지역 등록
        if (checked) {
            parent.parent.setLastSelectRegion(this);
        }
        if (parent != null) {
            parent.refreshCount();
            parent.parent.validate();
        }
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

    public static class Builder {
        String region_id;
        String region_name;
        String city_id;
        City parent;
        boolean isChecked = false;

        public Region create() {
            Region region = new Region();

            region.setRegion_id(region_id);
            region.setRegion_name(region_name);
            region.setCity_id(city_id);
            region.setParent(parent);
            region.setChecked(isChecked);

            return region;
        }

        public Builder setRegion_id(String region_id) {
            this.region_id = region_id;
            return this;
        }

        public Builder setCity_id(String city_id) {
            this.city_id = city_id;
            return this;
        }

        public Builder setRegion_name(String region_name) {
            this.region_name = region_name;
            return this;
        }

        public Builder setChecked(boolean checked) {
            isChecked = checked;
            return this;
        }

        public Builder setParent(City parent) {
            this.parent = parent;
            return this;
        }
    }
}
