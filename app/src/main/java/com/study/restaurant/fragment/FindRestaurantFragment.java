package com.study.restaurant.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.Region;
import com.study.restaurant.popup.SelectDistancePopup;
import com.study.restaurant.popup.SelectFilterPoppupActivity;
import com.study.restaurant.popup.SelectRegionPopupActivity;
import com.study.restaurant.popup.SelectSortPopupActivity;
import com.study.restaurant.presenter.FindRestaurantPresenter;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.FindRestaurantView;

public class FindRestaurantFragment extends Fragment implements FindRestaurantView {

    FindRestaurantPresenter findRestaurantPresenter;
    FragmentFindRestaurantBinding fragmentFindRestaurantBinding;
    Cities mCities = new Cities();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findRestaurantPresenter = new FindRestaurantPresenter(this);
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
        fragmentFindRestaurantBinding.setSort(((GlobalApplication) getActivity().getApplication()).getSort());
        fragmentFindRestaurantBinding.setBoundary(((GlobalApplication) getActivity().getApplication()).getBoundary());

        fragmentFindRestaurantBinding.setPresenter(findRestaurantPresenter);

        findRestaurantPresenter.initLocationManager(getActivity());
        //위치 요청하기
        findRestaurantPresenter.requestLocation(location -> {
            if (location != null) {
                LOG.d(location.toString());
                //내 위치가 있다면 현재 위치의 주소 확인하기
                if (!findRestaurantPresenter.requestAddress(location.getLatitude(), location.getLongitude())) {
                    Toast.makeText(getActivity(), "현재 주소를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                LOG.d("location is null");
            }
        });

        return fragmentFindRestaurantBinding.getRoot();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        findRestaurantPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setRegion(Region region) {
        fragmentFindRestaurantBinding.setRegion(region);
        fragmentFindRestaurantBinding.location.setText(region.getRegion_name());
    }

    @Override
    public void showSelectRegionPopup() {
        SelectRegionPopupActivity.show((AppCompatActivity) getActivity());
    }

    @Override
    public void showSortPopup() {
        SelectSortPopupActivity.show((AppCompatActivity) getActivity());
    }

    @Override
    public void showBoundaryPopup() {
        SelectDistancePopup.show((AppCompatActivity) getActivity());
    }

    @Override
    public void showFilterPopup() {
        SelectFilterPoppupActivity.show((AppCompatActivity) getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0x01) {
                mCities = ((GlobalApplication) getActivity().getApplication()).getCities();
                fragmentFindRestaurantBinding.setCities(mCities);
            }

            if (requestCode == 0x04) {

            }
        }

    }
}
