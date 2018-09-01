package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

public class NewsListFragment extends Fragment {

    RecyclerView rvNewsList;

    public NewsListFragment() {
        // Required empty public constructor
    }

    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);
        rvNewsList = v.findViewById(R.id.rvNewsList);
        rvNewsList.setAdapter(new RvAdt());
        rvNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    public class RvAdt extends RecyclerView.Adapter<RvHolder> {

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            RvHolder rvHolder = new RvHolder(v);
            return rvHolder;
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        public RvHolder(View itemView) {
            super(itemView);
        }
    }

}
