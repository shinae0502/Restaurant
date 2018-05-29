package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.study.restaurant.model.Store;
import com.study.restaurant.model.Story;
import com.study.restaurant.util.MyGlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StoryFragment extends Fragment {

    RecyclerView recyclerView;

    public StoryFragment() {
        // Required empty public constructor
    }

    public static StoryFragment newInstance() {
        StoryFragment fragment = new StoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_story, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, 1, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) return 2;
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new RvAdt());

        ApiManager.getInstance().getStory(new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Log.d("exceptionTag", "" + result);
                Type listType = new TypeToken<ArrayList<Story>>() {
                }.getType();
                List<Story> storyList = new Gson().fromJson(result, listType);
                ((RvAdt) recyclerView.getAdapter()).setStoryList(storyList);
            }

            @Override
            public void failed(String msg) {

            }
        });
        return v;
    }

    public class RvAdt extends RecyclerView.Adapter<RvHolder> {

        private List<Story> storyList = new ArrayList<>();

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v;
            if (viewType == 0)
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
            else
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story1, parent, false);
            RvHolder rvHolder = new RvHolder(v);
            return rvHolder;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {
            holder.title.setText(storyList.get(position).getTitle());
            holder.subTitle.setText(storyList.get(position).getSubtitle());
            MyGlide.with(holder.itemView.getContext())
                    .load(storyList.get(position).getImage())
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return storyList.size();
        }

        public void setStoryList(List<Story> storyList) {
            this.storyList = storyList;
            notifyDataSetChanged();
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;
        ImageView image;


        public RvHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            image = itemView.findViewById(R.id.img);
        }
    }
}
