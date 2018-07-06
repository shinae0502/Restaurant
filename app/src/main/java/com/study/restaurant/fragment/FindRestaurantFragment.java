package com.study.restaurant.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.model.FindRestaurant;
import com.study.restaurant.model.Region;
import com.study.restaurant.model.Store;
import com.study.restaurant.popup.SelectDistancePopup;
import com.study.restaurant.popup.SelectFilterPoppupActivity;
import com.study.restaurant.popup.SelectRegionPopupActivity;
import com.study.restaurant.popup.SelectSortPopupActivity;
import com.study.restaurant.presenter.FindRestaurantPresenter;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.FindRestaurantView;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.study.restaurant.adapter.ProgressRvAdt.VIEW_TYPE_PROGRESS;
import static com.study.restaurant.adapter.StoreRvAdt.VIEW_TYPE_BANNER;
import static com.study.restaurant.adapter.StoreRvAdt.VIEW_TYPE_MENU;

public class FindRestaurantFragment extends Fragment implements FindRestaurantView {

    FindRestaurantPresenter findRestaurantPresenter;
    FragmentFindRestaurantBinding fragmentFindRestaurantBinding;
    RecyclerView findRestaurantRv;
    ViewPager bannerPager;
    private FindRestaurantViewModel findRestaurantViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findRestaurantPresenter = new FindRestaurantPresenter((AppCompatActivity) getActivity(), this);
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

        findRestaurantViewModel = new FindRestaurantViewModel();
        fragmentFindRestaurantBinding.setVm(findRestaurantViewModel);
        FindRestaurant findRestaurant = getGlobalApplication().getFindRestaurant();
        findRestaurantViewModel.setFindRestaurant(findRestaurant);

        fragmentFindRestaurantBinding.setSort(((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getSort());
        fragmentFindRestaurantBinding.setBoundary(((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getBoundary());
        fragmentFindRestaurantBinding.setPresenter(findRestaurantPresenter);

        findRestaurantRv = fragmentFindRestaurantBinding.findRestaurantRv;
        findRestaurantRv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ((GridLayoutManager) findRestaurantRv.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (((StoreRvAdt) findRestaurantRv.getAdapter()).getItemViewType(position) == VIEW_TYPE_PROGRESS)
                    return 2;

                if (((StoreRvAdt) findRestaurantRv.getAdapter()).getItemViewType(position) == VIEW_TYPE_BANNER) {
                    return 2;
                }

                if (((StoreRvAdt) findRestaurantRv.getAdapter()).getItemViewType(position) == VIEW_TYPE_MENU) {
                    return 2;
                }

                return 1;
            }
        });
        //findRestaurantRv.setNestedScrollingEnabled(false);

        findRestaurantPresenter.initLocationManager(getActivity());
        requestAroundStore();

        return fragmentFindRestaurantBinding.getRoot();
    }

    @Override
    public void requestStoreSummary() {
        if (getGlobalApplication().getFindRestaurant().getCities() != null)
            findRestaurantPresenter.requestStoreSummery(getGlobalApplication().getFindRestaurant().getCities(),
                    getGlobalApplication().getFindRestaurant().getBoundary(),
                    getGlobalApplication().getFindRestaurant().getFilter(),
                    getGlobalApplication().getFindRestaurant().getSort(),
                    storeArrayList -> {
                        ((StoreRvAdt) findRestaurantRv.getAdapter()).setStoreList(storeArrayList);
                        fragmentFindRestaurantBinding.progress.setVisibility(View.GONE);
                        fragmentFindRestaurantBinding.findRestaurantRv.setVisibility(View.VISIBLE);
                    });
    }

    public void requestStoreSummary(Region region) {
        findRestaurantPresenter.requestStoreSummery(region,
                getGlobalApplication().getFindRestaurant().getBoundary(),
                getGlobalApplication().getFindRestaurant().getFilter(),
                getGlobalApplication().getFindRestaurant().getSort(),
                storeArrayList -> {
                    findRestaurantViewModel.setStoreArrayList(storeArrayList);
                    fragmentFindRestaurantBinding.progress.setVisibility(View.GONE);
                    fragmentFindRestaurantBinding.findRestaurantRv.setVisibility(View.VISIBLE);
                });
    }

    @Override
    public void requestAroundStore() {
        //위치 요청하기
        fragmentFindRestaurantBinding.progress.setVisibility(View.VISIBLE);
        fragmentFindRestaurantBinding.findRestaurantRv.setVisibility(View.GONE);
        findRestaurantPresenter.requestLocation(location -> {
            if (location != null) {
                LOG.d(location.toString());
                //내 위치가 있다면 현재 위치의 주소 확인하기
                if (!findRestaurantPresenter.requestAddress(location.getLatitude(), location.getLongitude(), region -> {
                    setRegion(region);
                    getGlobalApplication().getFindRestaurant().getCities().setCurrentRegion(region);
                    requestStoreSummary(region);
                    //서버 맛집 요청하기
                })) {
                    Toast.makeText(getActivity(), "현재 주소를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            } else {
                LOG.d("location is null");
            }
        });
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

    private GlobalApplication getGlobalApplication() {
        return (GlobalApplication) getActivity().getApplication();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0x01) {
                fragmentFindRestaurantBinding.setCities(((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getCities());
                fragmentFindRestaurantBinding.findRestaurantRv.setVisibility(View.GONE);
                fragmentFindRestaurantBinding.progress.setVisibility(View.VISIBLE);
                requestStoreSummary();
            }

            if (requestCode == 0x02 || requestCode == 0x03 || requestCode == 0x04) {
                fragmentFindRestaurantBinding.findRestaurantRv.setVisibility(View.GONE);
                fragmentFindRestaurantBinding.progress.setVisibility(View.VISIBLE);
                requestStoreSummary();
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

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BlankFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 10;
        }
    }

    public static class BlankFragment extends Fragment {
        public BlankFragment() {
        }

        public static BlankFragment newInstance() {
            BlankFragment fragment = new BlankFragment();
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_blank, container, false);
        }
    }
}
