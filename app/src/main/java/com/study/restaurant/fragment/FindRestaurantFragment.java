package com.study.restaurant.fragment;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.activity.RestaurantDetailActivity;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.databinding.ItemBinding;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.Region;
import com.study.restaurant.model.Store;
import com.study.restaurant.popup.SelectDistancePopup;
import com.study.restaurant.popup.SelectFilterPoppupActivity;
import com.study.restaurant.popup.SelectRegionPopupActivity;
import com.study.restaurant.popup.SelectSortPopupActivity;
import com.study.restaurant.presenter.FindRestaurantPresenter;
import com.study.restaurant.util.LOG;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.view.FindRestaurantView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class FindRestaurantFragment extends Fragment implements FindRestaurantView {

    FindRestaurantPresenter findRestaurantPresenter;
    FragmentFindRestaurantBinding fragmentFindRestaurantBinding;
    Cities mCities = new Cities();
    RecyclerView findRestaurantRv;
    ViewPager bannerPager;


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
        fragmentFindRestaurantBinding.setSort(((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getSort());
        fragmentFindRestaurantBinding.setBoundary(((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getBoundary());
        fragmentFindRestaurantBinding.setPresenter(findRestaurantPresenter);

        findRestaurantRv = fragmentFindRestaurantBinding.findRestaurantRv;
        findRestaurantRv.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        findRestaurantRv.setAdapter(new RvAdt());
        findRestaurantRv.setNestedScrollingEnabled(false);

        bannerPager = fragmentFindRestaurantBinding.bannerPager;
        bannerPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        CirclePageIndicator circlePageIndicator = fragmentFindRestaurantBinding.indicator;
        circlePageIndicator.setViewPager(bannerPager);


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
                        ((RvAdt) findRestaurantRv.getAdapter()).setStoreList(storeArrayList);
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
                    ((RvAdt) findRestaurantRv.getAdapter()).setStoreList(storeArrayList);
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
                mCities = ((GlobalApplication) getActivity().getApplication()).getFindRestaurant().getCities();
                fragmentFindRestaurantBinding.setCities(mCities);
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
                fragmentFindRestaurantBinding.layoutFilter.setSelected(
                        dirty
                );
            }
        }

    }

    public class RvAdt extends RecyclerView.Adapter<RvHolder> {

        List<Store> storeList = new ArrayList<>();

        public List<Store> getStoreList() {
            return storeList;
        }

        public void setStoreList(List<Store> storeList) {
            this.storeList = storeList;
            notifyDataSetChanged();
        }

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return RvHolder.create(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {
            storeList.get(position).setPosition(position);
            holder.setStore(storeList.get(position));
            MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.img);
            holder.itemBinding.parent.setOnClickListener(view -> RestaurantDetailActivity.go((AppCompatActivity) getActivity(), storeList.get(position)));
        }

        @Override
        public int getItemCount() {
            return storeList.size();
        }
    }

    public static class RvHolder extends RecyclerView.ViewHolder {
        ItemBinding itemBinding;

        public static RvHolder create(ViewGroup parent, int viewType) {
            ItemBinding itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new RvHolder(itemBinding);
        }

        ImageView img;

        public RvHolder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            img = itemView.findViewById(R.id.img);
        }

        public ItemBinding getItemBinding() {
            return itemBinding;
        }

        public void setStore(Store store) {
            itemBinding.setStore(store);
        }
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

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
