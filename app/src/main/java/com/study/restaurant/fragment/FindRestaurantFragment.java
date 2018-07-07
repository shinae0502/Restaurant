package com.study.restaurant.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.activity.SearchActivity;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.manager.MyLocationManager;
import com.study.restaurant.model.Region;
import com.study.restaurant.popup.SelectDistancePopup;
import com.study.restaurant.popup.SelectFilterPoppupActivity;
import com.study.restaurant.popup.SelectRegionPopupActivity;
import com.study.restaurant.popup.SelectSortPopupActivity;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.FindRestaurantNavigation;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.study.restaurant.adapter.ProgressRvAdt.VIEW_TYPE_PROGRESS;
import static com.study.restaurant.adapter.StoreRvAdt.VIEW_TYPE_BANNER;
import static com.study.restaurant.adapter.StoreRvAdt.VIEW_TYPE_MENU;

public class FindRestaurantFragment extends Fragment implements FindRestaurantNavigation {

    private FragmentFindRestaurantBinding fragmentFindRestaurantBinding;
    private RecyclerView findRestaurantRv;
    private FindRestaurantViewModel findRestaurantViewModel;
    private MyLocationManager myLocationManager;
    private OnSuccessListener<? super Location> tempListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //Sets Binding
        fragmentFindRestaurantBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_restaurant, container, false);

        //create ViewModel
        findRestaurantViewModel = new FindRestaurantViewModel(this);

        //Sets Binding ViewModel
        fragmentFindRestaurantBinding.setVm(findRestaurantViewModel);

        findRestaurantViewModel.setFindRestaurant(getGlobalApplication().getFindRestaurant());


        findRestaurantRv = fragmentFindRestaurantBinding.findRestaurantRv;
        findRestaurantRv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ((GridLayoutManager) findRestaurantRv.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (findRestaurantRv.getAdapter().getItemViewType(position) == VIEW_TYPE_PROGRESS)
                    return 2;

                if (VIEW_TYPE_BANNER == findRestaurantRv.getAdapter().getItemViewType(position)) {
                    return 2;
                }

                if (findRestaurantRv.getAdapter().getItemViewType(position) == VIEW_TYPE_MENU) {
                    return 2;
                }

                return 1;
            }
        });

        myLocationManager = new MyLocationManager(getActivity());


        //내 위치 요청하기
        requestLocation(location -> {
            if (location != null) {
                //현재 내 위치에 해당하는 지역 요청하기
                if (!requestAddress(location.getLatitude(), location.getLongitude(), region -> {
                    //타이틀바 지역명 변경하기
                    fragmentFindRestaurantBinding.location.setText(region.getRegion_name());

                    findRestaurantViewModel.getFindRestaurant().getCities().setCurrentRegion(region);
                    //내위치 해당 지역 맛집 검색하기
                    findRestaurantViewModel.requestStoreSummary();
                })) {
                    fragmentFindRestaurantBinding.location.setText("지역 없음");
                }
            } else {
                fragmentFindRestaurantBinding.location.setText("위치 찾기 실패");
            }
        });

        return fragmentFindRestaurantBinding.getRoot();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    public void goSearch() {
        SearchActivity.go((AppCompatActivity) getActivity());
    }

    private GlobalApplication getGlobalApplication() {
        return (GlobalApplication) getActivity().getApplication();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0x01) {
                fragmentFindRestaurantBinding.setCities(((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getCities());
                findRestaurantViewModel.getFindRestaurant().getCities().setCurrentRegion(null);
                findRestaurantViewModel.removeAllStore();
                findRestaurantViewModel.requestStoreSummary();
            }

            if (requestCode == 0x02 || requestCode == 0x03 || requestCode == 0x04) {
                findRestaurantViewModel.removeAllStore();
                findRestaurantViewModel.requestStoreSummary();
            }

            //필터 on/off 여부 파악하기
            if (requestCode == 0x04) {
                boolean dirty = ((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getFilter().isDirty();
                LOG.d(dirty);
                /*fragmentFindRestaurantBinding.layoutFilter.setSelected(
                        dirty
                );*/
            }
        }
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
                }
            }

            @Override
            public void failed(String msg) {

            }
        });
        return true;
    }
}
