package com.study.restaurant.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.study.restaurant.R;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.presenter.FindRestaurantPresenter;
import com.study.restaurant.util.LOG;

public class FindRestaurantFragment extends Fragment {

    FindRestaurantPresenter findRestaurantPresenter;
    FragmentFindRestaurantBinding fragmentFindRestaurantBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findRestaurantPresenter = new FindRestaurantPresenter();
        findRestaurantPresenter.initLocationManager(getActivity());
        //위치 요청하기
        findRestaurantPresenter.requestLocation(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LOG.d(location.toString());
                    //내 위치가 있다면 현재 위치의 주소 확인하기
                    if (!findRestaurantPresenter.requestAddress(location.getLatitude(), location.getLongitude())) {
                        Toast.makeText(getActivity(), "현재 주소를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    LOG.d("location is null");
                }
            }
        });
    }

    public FindRestaurantFragment() {
        // Required empty public constructor
    }

    public static FindRestaurantFragment newInstance() {
        FindRestaurantFragment fragment = new FindRestaurantFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFindRestaurantBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_restaurant, container, false);
        return fragmentFindRestaurantBinding.getRoot();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        findRestaurantPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
