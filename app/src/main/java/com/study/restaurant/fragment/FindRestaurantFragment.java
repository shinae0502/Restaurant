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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
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
                    ((RvAdt) findRestaurantRv.getAdapter())::setStoreList);
    }

    @Override
    public void requestAroundStore() {
        //위치 요청하기
        findRestaurantPresenter.requestLocation(location -> {
            if (location != null) {
                LOG.d(location.toString());
                //내 위치가 있다면 현재 위치의 주소 확인하기
                if (!findRestaurantPresenter.requestAddress(location.getLatitude(), location.getLongitude(), region -> {
                    setRegion(region);

                    requestStoreSummary();
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
                requestStoreSummary();
            }

            if (requestCode == 0x04) {

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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            RvHolder rvHolder = new RvHolder(v);
            return rvHolder;
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {
            holder.title.setText((position + 1) + ". " + storeList.get(position).getName());
            MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .into(holder.img);

            holder.region.setText(storeList.get(position).getLocation());
            holder.distances.setText("2.68km");
            holder.view.setText(storeList.get(position).getHit());
            holder.review.setText(storeList.get(position).getReview_count());
            holder.score.setText(storeList.get(position).getScore());
        }

        @Override
        public int getItemCount() {
            return storeList.size();
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;
        TextView region;
        TextView distances;
        TextView view;
        TextView review;
        TextView score;

        public RvHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
            region = itemView.findViewById(R.id.region);
            distances = itemView.findViewById(R.id.distances);
            view = itemView.findViewById(R.id.view);
            review = itemView.findViewById(R.id.review);
            score = itemView.findViewById(R.id.score);
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
