package com.study.restaurant.manager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MyLocationManager {

    private static MyLocationManager myLocationManager;
    FusedLocationProviderClient mFusedLocationClient;
    Activity activity;

    public MyLocationManager(Activity activity) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        this.activity = activity;
    }

    public static MyLocationManager getInstance(Activity activity) {
        if (myLocationManager == null)
            myLocationManager = new MyLocationManager(activity);
        return myLocationManager;
    }

    void requestLocationPermissionPopup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0x01);
        }
    }

    public void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermissionPopup();
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Toast.makeText(activity, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
