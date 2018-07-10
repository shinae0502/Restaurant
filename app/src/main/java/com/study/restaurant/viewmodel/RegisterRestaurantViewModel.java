package com.study.restaurant.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.study.restaurant.BR;
import com.study.restaurant.view.RegisterRestaurantNavigator;

public class RegisterRestaurantViewModel extends BaseObservable {
    private RegisterRestaurantNavigator registerRestaurantNavigator;
    private String restaurantName;
    private String phone;
    private boolean selectBar;
    private boolean selectCafe;
    private boolean selectBuffet;
    private boolean selectWorldWideFood;
    private boolean selectWesternFood;
    private boolean selectChineseFood;
    private boolean selectJapaneseFood;
    private boolean selectKoreanFood;
    private String location;


    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.add);
    }

    public RegisterRestaurantNavigator getRegisterRestaurantNavigator() {
        return registerRestaurantNavigator;
    }

    public void setRegisterRestaurantNavigator(RegisterRestaurantNavigator registerRestaurantNavigator) {
        this.registerRestaurantNavigator = registerRestaurantNavigator;
    }

    @Bindable
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelectCafe() {
        return selectCafe;
    }

    public void setSelectCafe(boolean selectCafe) {
        this.selectCafe = selectCafe;
        if (selectCafe) {
            setSelectBar(false);
            setSelectWorldWideFood(false);
            setSelectBuffet(false);
            setSelectJapaneseFood(false);
            setSelectKoreanFood(false);
            setSelectChineseFood(false);
            setSelectWesternFood(false);
        }
        notifyPropertyChanged(BR.selectCafe);
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelectBuffet() {
        return selectBuffet;
    }

    public void setSelectBuffet(boolean selectBuffet) {
        this.selectBuffet = selectBuffet;
        if (selectBuffet) {
            setSelectBar(false);
            setSelectWorldWideFood(false);
            setSelectCafe(false);
            setSelectJapaneseFood(false);
            setSelectKoreanFood(false);
            setSelectChineseFood(false);
            setSelectWesternFood(false);
        }
        notifyPropertyChanged(BR.selectBuffet);
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelect() {
        return selectWorldWideFood;
    }

    public void setSelectWorldWideFood(boolean selectWorldWideFood) {
        this.selectWorldWideFood = selectWorldWideFood;
        if (selectWorldWideFood) {
            setSelectBar(false);
            setSelectBuffet(false);
            setSelectCafe(false);
            setSelectJapaneseFood(false);
            setSelectKoreanFood(false);
            setSelectChineseFood(false);
            setSelectWesternFood(false);
        }
        notifyPropertyChanged(BR.selectWorldWideFood);
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelectWesternFood() {
        return selectWesternFood;
    }


    public void setSelectWesternFood(boolean selectWesternFood) {
        this.selectWesternFood = selectWesternFood;
        if (selectWesternFood) {
            setSelectBar(false);
            setSelectBuffet(false);
            setSelectCafe(false);
            setSelectJapaneseFood(false);
            setSelectKoreanFood(false);
            setSelectChineseFood(false);
            setSelectWorldWideFood(false);
        }
        notifyPropertyChanged(BR.selectWesternFood);
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelectChineseFood() {
        return selectChineseFood;
    }

    public void setSelectChineseFood(boolean selectChineseFood) {
        this.selectChineseFood = selectChineseFood;
        if (selectChineseFood) {
            setSelectBar(false);
            setSelectBuffet(false);
            setSelectCafe(false);
            setSelectJapaneseFood(false);
            setSelectKoreanFood(false);
            setSelectWesternFood(false);
            setSelectWorldWideFood(false);
        }
        notifyPropertyChanged(BR.selectChineseFood);
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelectJapaneseFood() {
        return selectJapaneseFood;
    }

    public void setSelectJapaneseFood(boolean selectJapaneseFood) {
        this.selectJapaneseFood = selectJapaneseFood;
        if (selectJapaneseFood) {
            setSelectBar(false);
            setSelectBuffet(false);
            setSelectCafe(false);
            setSelectChineseFood(false);
            setSelectKoreanFood(false);
            setSelectWesternFood(false);
            setSelectWorldWideFood(false);
        }
        notifyPropertyChanged(BR.selectJapaneseFood);
        notifyPropertyChanged(BR.add);
    }

    @Bindable
    public boolean isSelectKoreanFood() {
        return selectKoreanFood;
    }

    public void setSelectKoreanFood(boolean selectKoreanFood) {
        this.selectKoreanFood = selectKoreanFood;
        if (selectKoreanFood) {
            setSelectBar(false);
            setSelectBuffet(false);
            setSelectCafe(false);
            setSelectChineseFood(false);
            setSelectJapaneseFood(false);
            setSelectWesternFood(false);
            setSelectWorldWideFood(false);
        }
        notifyPropertyChanged(BR.selectKoreanFood);
        notifyPropertyChanged(BR.add);
    }

    public void clickKoreanFood(View v) {
        setSelectKoreanFood(!isSelectKoreanFood());
    }

    public void clickJapaneseFood(View v) {
        setSelectJapaneseFood(!isSelectJapaneseFood());
    }

    public void clickChineseFood(View v) {
        setSelectChineseFood(!isSelectChineseFood());
    }

    public void clickWesternFood(View v) {
        setSelectWesternFood(!isSelectWesternFood());
    }

    @Bindable
    public boolean isSelectWorldWideFood() {
        return selectWorldWideFood;
    }

    public void clickWorldWideFood(View v) {
        setSelectWorldWideFood(!isSelectWorldWideFood());
    }

    public void clickBuffet(View v) {
        setSelectBuffet(!isSelectBuffet());
    }

    @Bindable
    public boolean isSelectBar() {
        return selectBar;
    }

    public void setSelectBar(boolean selectBar) {
        this.selectBar = selectBar;
        if (selectBar) {
            setSelectKoreanFood(false);
            setSelectBuffet(false);
            setSelectCafe(false);
            setSelectChineseFood(false);
            setSelectJapaneseFood(false);
            setSelectWesternFood(false);
            setSelectWorldWideFood(false);
        }
        notifyPropertyChanged(BR.selectBar);
        notifyPropertyChanged(BR.add);
    }

    public void clickBar(View v) {
        setSelectBar(!isSelectBar());
    }

    public void clickCafe(View v) {
        setSelectCafe(!isSelectCafe());
    }


    public RegisterRestaurantViewModel(RegisterRestaurantNavigator registerRestaurantNavigator) {
        this.registerRestaurantNavigator = registerRestaurantNavigator;
    }

    public void clickLocation(View v) {

    }

    @Bindable
    public boolean isAdd() {
        return !TextUtils.isEmpty(restaurantName) && !TextUtils.isEmpty(location) &&
                (
                        isSelectWorldWideFood() || isSelectCafe() || isSelectBar()
                                || isSelectKoreanFood() || isSelectJapaneseFood() || isSelectChineseFood()
                                || isSelectWesternFood() || isSelectBar() || isSelectBuffet()
                );
    }

    @Bindable
    public TextWatcher getNameTextWhatcher()
    {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setRestaurantName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    @Bindable
    public TextWatcher getLocationWhatcher()
    {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setLocation(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    public void add(View v)
    {
        if(!TextUtils.isEmpty(restaurantName))
        Log.d("sarang", restaurantName);
        if(!TextUtils.isEmpty(location))
        Log.d("sarang", location);
        if(!TextUtils.isEmpty(phone))
        Log.d("sarang", phone);

        if(selectBar)
            Log.d("sarang", "selectBar");
        if(selectCafe)
            Log.d("sarang", "selectCafe");
        if(selectBuffet)
            Log.d("sarang", "selectBuffet");
        if(selectWorldWideFood)
            Log.d("sarang", "selectWorldWideFood");
        if(selectWesternFood)
            Log.d("sarang", "selectWesternFood");
        if(selectChineseFood)
            Log.d("sarang", "selectChineseFood");
        if(selectJapaneseFood)
            Log.d("sarang", "selectJapaneseFood");
        if(selectKoreanFood)
            Log.d("sarang", "selectKoreanFood");

    }

}
