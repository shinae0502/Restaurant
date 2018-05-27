package com.study.restaurant.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Store;
import com.viewpagerindicator.CirclePageIndicator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FindRestaurantFragment extends Fragment implements FunctionImpl.FindRestaurant {

    private RecyclerView findRestaurantRv;
    private ViewPager bannerPager;

    public FindRestaurantFragment() {
        // Required empty public constructor
    }

    public static FindRestaurantFragment newInstance() {
        FindRestaurantFragment fragment = new FindRestaurantFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_find_restaurant, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar((Toolbar) v.findViewById(R.id.toolbar));

        findRestaurantRv = v.findViewById(R.id.findRestaurantRv);
        findRestaurantRv.setLayoutManager(new LinearLayoutManager(getContext()));
        findRestaurantRv.setAdapter(new RvAdt());
        findRestaurantRv.setNestedScrollingEnabled(false);

        bannerPager = v.findViewById(R.id.bannerPager);
        bannerPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));

        CirclePageIndicator circlePageIndicator = v.findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(bannerPager);

        TextView textView = v.findViewById(R.id.location);
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView filter = v.findViewById(R.id.filter);
        filter.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        ApiManager.getInstance().getStoreSummary(new Store(), new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Log.d("exceptionTag", "" + result);
                Type listType = new TypeToken<ArrayList<Store>>() {
                }.getType();
                List<Store> storeList = new Gson().fromJson(result, listType);
                ((RvAdt) findRestaurantRv.getAdapter()).setStoreList(storeList);
            }

            @Override
            public void failed(String msg) {
            }
        });

        return v;
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
            holder.title.setText(storeList.get(position).getName());
            MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .into(holder.img);
        }

        @Override
        public int getItemCount() {
            return storeList.size();
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;

        public RvHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            img = itemView.findViewById(R.id.img);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickSelectLocation() {
        //TODO::
    }

    @Override
    public void showSelectLocationPopup() {
        //TODO::
    }

    @Override
    public void clickSearch() {
        //TODO::
    }

    @Override
    public void goSearch() {
        //TODO::
    }

    @Override
    public void clickMap() {
        //TODO::
    }

    @Override
    public void goMap() {
        //TODO::
    }

    @Override
    public void clickBanner() {
        //TODO::
    }

    @Override
    public void goBanner() {
        //TODO::
    }

    @Override
    public void clickSort() {
        //TODO::
    }

    @Override
    public void showSelectSortItemPopup() {
        //TODO::
    }

    @Override
    public void clickBoundary() {
        //TODO::
    }

    @Override
    public void showSelectBoundaryPopup() {
        //TODO::
    }

    @Override
    public void clickFilter() {
        //TODO::
    }

    @Override
    public void showSelectFilterPopup() {
        //TODO::
    }

    @Override
    public void clickEventBanner() {
        //TODO::
    }

    @Override
    public void goEvent() {
        //TODO::
    }

    @Override
    public void clickListItem() {
        //TODO::
    }

    @Override
    public void goRestaurant() {
        //TODO::
    }

    @Override
    public void setShowTopButton(boolean show) {
        //TODO::
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
