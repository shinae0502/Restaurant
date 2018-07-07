package com.study.restaurant.presenter;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.activity.SearchActivity;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.model.Boundary;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.Filter;
import com.study.restaurant.model.Region;
import com.study.restaurant.model.Sort;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.FindRestaurantNavigation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindRestaurantPresenter {
    MyLocationManager myLocationManager;
    OnSuccessListener<? super Location> tempListener;
    FindRestaurantNavigation findRestaurantView;
    AppCompatActivity appCompatActivity;

    public FindRestaurantPresenter(AppCompatActivity appCompatActivity, FindRestaurantNavigation findRestaurantView) {
        this.appCompatActivity = appCompatActivity;
        this.findRestaurantView = findRestaurantView;
    }

    public void initLocationManager(Activity activity) {
        myLocationManager = new MyLocationManager(activity);
    }

    public void requestLocation(OnSuccessListener<? super Location> listener) {
        //GPS 사용 전 권한 체크
        if (myLocationManager.isGrantedPermission()) {
            //권한이 허용되어있다면 위치요청
            LOG.d("위치요청하기");
            myLocationManager.getLastLocation(listener);
        } else {
            //권한이 없다면 권한 요청하기
            tempListener = listener;
            LOG.d("권한요청하기");
            myLocationManager.requestLocationPermissionPopup(0x02);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0x02) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //위치 다시 요청하기
                if (tempListener != null) {
                    requestLocation(tempListener);
                    tempListener = null;
                }
            } else {
                //권한 거부시
                LOG.d("권한 거부");
                tempListener = null;
            }
        }
    }

    public interface OnReceiveStoreListener {
        void onReceiveStore(ArrayList<Store> storeArrayList);
    }

    public void requestStoreSummery(Cities cities, Boundary boundary, Filter filter, Sort sort, OnReceiveStoreListener onReceiveStoreListener) {

        HashMap<String, String> param = new HashMap<>();
        param.put("region_id", cities.getSelectedRegionIds());
        param.put("boundary", "");
        param.put("filter", "");
        param.put("sort", "");

        ApiManager.getInstance().getStoreSummary(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                LOG.d(result);
                Type listType = new TypeToken<ArrayList<Store>>() {
                }.getType();
                List<Store> storeList = new Gson().fromJson(result, listType);
                onReceiveStoreListener.onReceiveStore((ArrayList<Store>) storeList);
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    public void requestStoreSummery(Region region, Boundary boundary, Filter filter, Sort sort, OnReceiveStoreListener onReceiveStoreListener) {

        HashMap<String, String> param = new HashMap<>();
        param.put("region_id", region.getRegion_id());
        param.put("boundary", "");
        param.put("filter", "");
        param.put("sort", sort.getAttribute());

        ApiManager.getInstance().getStoreSummary(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                LOG.d(result);
                Type listType = new TypeToken<ArrayList<Store>>() {
                }.getType();
                List<Store> storeList = new Gson().fromJson(result, listType);
                onReceiveStoreListener.onReceiveStore((ArrayList<Store>) storeList);
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    public interface OnReceiveRegionListener {
        void onReceiveRegion(Region region);
    }

    public boolean requestAddress(double latitude, double longitude, OnReceiveRegionListener onReceiveRegionListener) {
        String zipCode = myLocationManager.getZipcode(latitude, longitude);
        if (zipCode.equals("")) {
            return false;
        }
        LOG.d(zipCode);
        ApiManager.getInstance().getRegion(zipCode, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type listType = new TypeToken<ArrayList<Region>>() {
                }.getType();
                List<Region> regionList = new Gson().fromJson(result, listType);
                if (regionList != null && regionList.size() > 0) {
                    onReceiveRegionListener.onReceiveRegion(regionList.get(0));
                    //findRestaurantView.setRegion(regionList.get(0));
                }
            }

            @Override
            public void failed(String msg) {

            }
        });
        return true;
    }
}
