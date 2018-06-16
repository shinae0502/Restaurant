package com.study.restaurant.presenter;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.util.LOG;

public class FindRestaurantPresenter {
    MyLocationManager myLocationManager;

    OnSuccessListener<? super Location> tempListener;

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

    public boolean requestAddress(double latitude, double longitude) {
        String zipCode = myLocationManager.getZipcode(latitude, longitude);
        if (zipCode.equals("")) {
            return false;
        }
        LOG.d(zipCode);
        return true;
    }
}
