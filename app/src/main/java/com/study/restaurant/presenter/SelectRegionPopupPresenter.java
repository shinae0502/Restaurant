package com.study.restaurant.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Region;
import com.study.restaurant.view.SelectRegionPopupView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SelectRegionPopupPresenter implements FunctionImpl.RegionPopup {

    SelectRegionPopupView selectRegionPopupView;

    public SelectRegionPopupPresenter(SelectRegionPopupView selectRegionPopupView)
    {
        this.selectRegionPopupView = selectRegionPopupView;
    }

    private Cities mCities = new Cities();

    public Cities getCities() {
        return mCities;
    }

    public void setCities(Cities mCities) {
        this.mCities = mCities;
    }

    /**
     * 도시 목록 요청하기
     *
     * @param onReceiveCityListener
     */
    @Override
    public void requestCity(final OnReceiveCityListener onReceiveCityListener) {
        ApiManager.getInstance().getCity(new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type listType = new TypeToken<ArrayList<City>>() {
                }.getType();
                ArrayList<City> cityArrayList = new Gson().fromJson(result, listType);
                onReceiveCityListener.onReceiveCity(cityArrayList);
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    /**
     * 지역 목록 요청하기
     *
     * @param onReceiveRegionListener
     */
    @Override
    public void requestRegion(final OnReceiveRegionListener onReceiveRegionListener) {
        ApiManager.getInstance().getRegion(null, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type listType = new TypeToken<ArrayList<Region>>() {
                }.getType();
                ArrayList<Region> regionArrayList = new Gson().fromJson(result, listType);
                onReceiveRegionListener.onReceiveRegion(regionArrayList);
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    public void validateButton() {
        //selectRegionPopupView.validateButton(getCities().isDirty());
    }

    public void initRegionAncCity(OnRegionAncCityListener onRegionAncCityListener) {
        //도시 불러오기
        requestCity(cityArrayList -> {
            mCities.setCities(cityArrayList);

            //지역 불러오기
            requestRegion(regionArrayList -> {
                mCities.setRegions(regionArrayList);
                if(onRegionAncCityListener != null)
                    onRegionAncCityListener.onReceiveRegionAndCity(mCities);
            });
        });
    }

    public interface OnRegionAncCityListener
    {
        void onReceiveRegionAndCity(Cities cities);
    }
}
