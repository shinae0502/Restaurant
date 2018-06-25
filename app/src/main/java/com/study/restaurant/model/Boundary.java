package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.study.restaurant.BR;

public class Boundary extends BaseObservable {
    boolean level1;
    boolean level2;
    boolean level3 = true;
    boolean level4;
    boolean level5;

    @Bindable
    public boolean isLevel1() {
        return level1;
    }

    public void setLevel1(boolean level1) {
        this.level1 = level1;
        notifyPropertyChanged(BR.level1);
        notifyPropertyChanged(BR.boundary);

    }

    @Bindable
    public boolean isLevel2() {
        return level2;
    }

    public void setLevel2(boolean level2) {
        this.level2 = level2;
        notifyPropertyChanged(BR.level2);
        notifyPropertyChanged(BR.boundary);
    }

    @Bindable
    public boolean isLevel3() {
        return level3;
    }

    public void setLevel3(boolean level3) {
        this.level3 = level3;
        notifyPropertyChanged(BR.level3);
        notifyPropertyChanged(BR.boundary);
    }

    @Bindable
    public boolean isLevel4() {
        return level4;
    }

    public void setLevel4(boolean level4) {
        this.level4 = level4;
        notifyPropertyChanged(BR.level4);
        notifyPropertyChanged(BR.boundary);
    }

    @Bindable
    public boolean isLevel5() {
        return level5;
    }

    public void setLevel5(boolean level5) {
        this.level5 = level5;
        notifyPropertyChanged(BR.level5);
        notifyPropertyChanged(BR.boundary);
    }

    @Bindable
    public String getBoundary() {
        return level1 ? "100m"
                : level2 ? "300m"
                : level3 ? "500m"
                : level4 ? "1km"
                : level5 ? "3km" : "없음";
    }
}
