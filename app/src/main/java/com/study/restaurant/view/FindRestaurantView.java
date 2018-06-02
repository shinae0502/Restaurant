package com.study.restaurant.view;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.MyGlide;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class FindRestaurantView extends CommonView {

    private FragmentFindRestaurantBinding fragmentFindRestaurantBinding;
    private AppCompatActivity appCompatActivity;

    public FindRestaurantView(Activity activity, FragmentFindRestaurantBinding fragmentFindRestaurantBinding) {
        this.fragmentFindRestaurantBinding = fragmentFindRestaurantBinding;
        appCompatActivity = (AppCompatActivity) activity;
    }

    public void init() {
        appCompatActivity.setSupportActionBar(fragmentFindRestaurantBinding.toolbar);
        fragmentFindRestaurantBinding.findRestaurantRv.setLayoutManager(new GridLayoutManager(appCompatActivity, 2, GridLayoutManager.VERTICAL, false));
        fragmentFindRestaurantBinding.findRestaurantRv.setAdapter(new RvAdt());
        fragmentFindRestaurantBinding.findRestaurantRv.setNestedScrollingEnabled(false);

        fragmentFindRestaurantBinding.bannerPager.setAdapter(new ViewPagerAdapter(appCompatActivity.getSupportFragmentManager()));

        CirclePageIndicator circlePageIndicator = fragmentFindRestaurantBinding.indicator;
        circlePageIndicator.setViewPager(fragmentFindRestaurantBinding.bannerPager);

        TextView textView = fragmentFindRestaurantBinding.location;
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView filter = fragmentFindRestaurantBinding.filter;
        filter.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public void setStoreList(List<Store> storeList) {
        ((RvAdt) fragmentFindRestaurantBinding.findRestaurantRv.getAdapter()).setStoreList(storeList);
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


