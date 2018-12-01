package com.study.restaurant.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityMapsBinding;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.Logger;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ActivityMapsBinding activityMapsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMapsBinding = DataBindingUtil.setContentView(this, R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        activityMapsBinding.title.addTextChangedListener(textWatcher);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setOnCameraIdleListener(() -> activityMapsBinding.title.setText(MyLocationManager.getAddress(getBaseContext(), mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude)));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    public static void go(AppCompatActivity appCompatActivity) {
        appCompatActivity.startActivityForResult(new Intent(appCompatActivity, MapsActivity.class), 0x05);
    }

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, MapsActivity.class);
        intent.putExtra("stroe", store);
        appCompatActivity.startActivity(intent);
    }

    private Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    public void clickBackBtn(View v) {
        finish();
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Logger.d(charSequence.toString());
            if (charSequence.toString().length() > 0) {
                activityMapsBinding.setting.setSelected(true);
            } else {
                activityMapsBinding.setting.setSelected(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void setting(View view) {
        if (view.isSelected()) {
            Intent intent = new Intent();
            intent.putExtra("lat", "" + mMap.getCameraPosition().target.latitude);
            intent.putExtra("lng", "" + mMap.getCameraPosition().target.longitude);
            intent.putExtra("address", activityMapsBinding.title.getText().toString());
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
