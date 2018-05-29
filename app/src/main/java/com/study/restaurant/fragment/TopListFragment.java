package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Story;
import com.study.restaurant.model.TopList;
import com.study.restaurant.util.MyGlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TopListFragment extends Fragment {

    RecyclerView recyclerView;

    public TopListFragment() {
        // Required empty public constructor
    }

    public static TopListFragment newInstance() {
        TopListFragment fragment = new TopListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_list, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RvAdt());

        ApiManager.getInstance().getTopList(new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Log.d("exceptionTag", "" + result);
                Type list = new TypeToken<ArrayList<TopList>>() {
                }.getType();
                List<TopList> topList = new Gson().fromJson(result, list);
                ((RvAdt) recyclerView.getAdapter()).setTopList(topList);
            }

            @Override
            public void failed(String msg) {

            }
        });

        return v;
    }

    public class RvAdt extends RecyclerView.Adapter<RvHolder> {

        private List<TopList> topList = new ArrayList<>();

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_list, parent, false);
            RvHolder rvHolder = new RvHolder(v);
            return rvHolder;
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {
            holder.title.setText(topList.get(position).getTitle());
            holder.subTitle.setText(topList.get(position).getSubtitle());
            holder.view.setText(topList.get(position).getHit());
            holder.date.setText("1 일 전");
            MyGlide.with(holder.itemView.getContext())
                    .load(topList.get(position).getImage())
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return topList.size();
        }

        public void setTopList(List<TopList> topList) {
            this.topList = topList;
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;
        ImageView image;
        TextView view;
        TextView date;


        public RvHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            view = itemView.findViewById(R.id.view);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.img);
        }
    }
}
