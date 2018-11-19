package com.study.restaurant.manager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.study.restaurant.util.Logger;

public class MyLocationManager {

    FusedLocationProviderClient mFusedLocationClient;
    Activity activity;

    public MyLocationManager(Activity activity) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        this.activity = activity;
    }

    public void requestLocationPermissionPopup(int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, i);
        }
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation(OnSuccessListener<? super Location> listener) {
        if (!isGrantedPermission()) {
            Logger.d("권한 없음");
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, listener);
    }

    public boolean isGrantedPermission() {
        return !(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
    }

    public String getZipcode(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(activity);
        try {
            return geocoder.getFromLocation(latitude, longitude, 1).get(0).getPostalCode();
        } catch (Exception e) {
            Logger.d(e.toString());
            return "";
        }
    }

    public static String getAddress(Context context, double latitude, double longitude)
    {
        Geocoder geocoder = new Geocoder(context);
        try {
            Address address = geocoder.getFromLocation(latitude, longitude, 1).get(0);
            return address.getCountryName() + " " + address.getFeatureName() + " " + address.getLocality();
        } catch (Exception e) {
            Logger.d(e.toString());
            return "주소 못찾음";
        }
    }
}
