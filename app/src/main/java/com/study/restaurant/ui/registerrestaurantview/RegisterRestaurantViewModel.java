package com.study.restaurant.ui.registerrestaurantview;

import android.database.Observable;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.google.gson.Gson;
import com.study.restaurant.BR;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.model.CommonResponse;
import com.study.restaurant.model.FoodCategotyModel;
import com.study.restaurant.util.Logger;
import com.study.restaurant.view.RegisterRestaurantNavigator;

import java.util.HashMap;
import java.util.Map;

public class RegisterRestaurantViewModel extends BaseObservable {
    private RegisterRestaurantNavigator registerRestaurantNavigator;
    private String restaurantName;
    private String phone;
    private String lat;
    private String lng;
    private String location;

    /*Observable<Boolean> isSelectKoreanFood;
    Observable<Boolean> isSelectJapaneseFood;
    Observable<Boolean> isSelectChineseFood;
    Observable<Boolean> isSelectWesternFood;
    Observable<Boolean> isSelectWorldWideFood;
    Observable<Boolean> isSelectBuffet;
    Observable<Boolean> isSelectCafe;
    Observable<Boolean> isSelectBar;
    Observable<String> restaurantName;
    Observable<String> address;
    Observable<String> latitude;
    Observable<String> longitude;
    Observable<Boolean> isRequiredField;
    Observable<Integer> getSelectedFood;*/

    FoodCategotyModel foodCategotyModel = new FoodCategotyModel();


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public FoodCategotyModel getFoodCategotyModel() {
        return foodCategotyModel;
    }

    public void setFoodCategotyModel(FoodCategotyModel foodCategotyModel) {
        this.foodCategotyModel = foodCategotyModel;
    }

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
        notifyPropertyChanged(BR.location);
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
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.CAFE);
    }

    @Bindable
    public boolean isSelectBuffet() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.BUFFET);
    }

    @Bindable
    public boolean isSelectWorldWideFood() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.WORLD_WIDE_FOOD);
    }

    @Bindable
    public boolean isSelectWesternFood() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.WESTERN_FOOD);
    }

    @Bindable
    public boolean isSelectChineseFood() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.CHINESE_FOOD);
    }

    @Bindable
    public boolean isSelectJapaneseFood() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.JAPANESE_FOOD);
    }

    @Bindable
    public boolean isSelectKoreanFood() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.KOREAN_FOOD);
    }

    @Bindable
    public boolean isSelectBar() {
        return foodCategotyModel.isSelected(FoodCategotyModel.FoodCategory.BAR);
    }

    public void clickKoreanFood(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.KOREAN_FOOD);
        foodCategoryNofity();
        //Logger.d(isSelectKoreanFood());
    }

    public void clickJapaneseFood(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.JAPANESE_FOOD);
        foodCategoryNofity();
    }

    public void clickChineseFood(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.CHINESE_FOOD);
        foodCategoryNofity();
    }

    public void clickWesternFood(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.WESTERN_FOOD);
        foodCategoryNofity();
    }

    public void clickWorldWideFood(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.WORLD_WIDE_FOOD);
        foodCategoryNofity();
    }

    public void clickBuffet(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.BUFFET);
        foodCategoryNofity();
    }

    public void clickBar(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.BAR);
        foodCategoryNofity();
    }

    public void clickCafe(View v) {
        Logger.d("");
        foodCategotyModel.setFoodCategory(FoodCategotyModel.FoodCategory.CAFE);
        foodCategoryNofity();
    }

    public void foodCategoryNofity() {
        notifyPropertyChanged(BR.selectKoreanFood);
        notifyPropertyChanged(BR.selectJapaneseFood);
        notifyPropertyChanged(BR.selectChineseFood);
        notifyPropertyChanged(BR.selectWesternFood);
        notifyPropertyChanged(BR.selectWorldWideFood);
        notifyPropertyChanged(BR.selectBuffet);
        notifyPropertyChanged(BR.selectCafe);
        notifyPropertyChanged(BR.selectBar);
        notifyPropertyChanged(BR.add);
    }


    public RegisterRestaurantViewModel(RegisterRestaurantNavigator registerRestaurantNavigator) {
        this.registerRestaurantNavigator = registerRestaurantNavigator;
    }

    public void clickLocation(View v) {
        registerRestaurantNavigator.goMap();
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
    public TextWatcher getNameTextWhatcher() {
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
    public TextWatcher getLocationWhatcher() {
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

    public void add(View v) {
        Map<String, String> param = new HashMap<>();
        if (!TextUtils.isEmpty(restaurantName)) {
            param.put("store_name", restaurantName);
        }
        if (!TextUtils.isEmpty(location)) {
            param.put("address", location);
        }
        if (!TextUtils.isEmpty(phone)) {
            param.put("phone", phone);
        }
        if (!TextUtils.isEmpty(lat)) {
            param.put("lat", lat);
        }
        if (!TextUtils.isEmpty(lng)) {
            param.put("lon", lng);
        }

        param.put("user_id", BananaPreference.getInstance(v.getContext()).loadUser().user_id);

        ApiManager.getInstance().regStore(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                CommonResponse commonResponse = new Gson().fromJson(result, CommonResponse.class);
                if (commonResponse.getResult().equals("0")) {
                    registerRestaurantNavigator.onFinish();
                }
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

}
