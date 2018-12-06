package com.study.restaurant.ui.restaurantdetailmapview;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityRestaurantDetailMapBinding;
import com.study.restaurant.model.Store;

/**
 * layout {@link R.layout#activity_restaurant_detail_map}
 */
public class RestaurantDetailMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ActivityRestaurantDetailMapBinding activityRestaurantDetailMapBinding;

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, RestaurantDetailMapActivity.class);
        intent.putExtra("store", store);
        appCompatActivity.startActivity(intent);
    }

    private Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantDetailMapBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail_map);
        activityRestaurantDetailMapBinding.titleBar.setTitle(getStore().getStore_name());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        float lat = 0;
        float lon = 0;
        try {
            lat = Float.valueOf(getStore().getLat());
            lon = Float.valueOf(getStore().getLon());
            LatLng sydney = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getStore().getStore_name()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));
        } catch (Exception e) {
            Toast.makeText(this, "맛집 위치를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickClose(View v) {
        finish();
    }
}
