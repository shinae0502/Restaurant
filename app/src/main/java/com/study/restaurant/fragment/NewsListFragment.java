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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.News;
import com.study.restaurant.util.MyGlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        //뉴스 피드 요청하기
        Map<String, String> param = new HashMap<>();
        ApiManager.getInstance().getNews(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type type = new TypeToken<ArrayList<News>>() {
                }.getType();
                ArrayList<News> newsArrayList = new Gson().fromJson(result, type);
                ((RvAdt) rvNewsList.getAdapter()).setNewsList(newsArrayList);
            }

            @Override
            public void failed(String msg) {

            }
        });
        return v;
    }

    public class RvAdt extends RecyclerView.Adapter<RvHolder> {

        private ArrayList<News> newsList;

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            RvHolder rvHolder = new RvHolder(v);
            return rvHolder;
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {
            News news = newsList.get(position);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getProfile_img())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.profilePic);

            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage1())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image1);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage2())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image2);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage3())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image3);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage4())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image4);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage5())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image5);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage6())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image6);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage7())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image7);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage8())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image8);


            holder.contents.setText(news.getContents());
            holder.date.setText(news.getDate());
            holder.likeCount.setText(news.getLike_count());
            holder.replyCount.setText(news.getReply_count());
            holder.tag1.setText(news.getHash_tag());
            holder.userFollower.setText(news.getUser_follower());
            holder.userName.setText(news.getUser_name());
            holder.userWriting.setText(news.getUser_writing());

        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (newsList != null)
                count = newsList.size();
            return count;
        }

        public void setNewsList(ArrayList<News> newsList) {
            this.newsList = newsList;
            notifyDataSetChanged();
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {
        ImageView profilePic;
        TextView userName;
        TextView userWriting;
        TextView userFollower;
        TextView tag1;
        TextView tag2;
        TextView contents;
        ImageView image1;
        ImageView image2;
        ImageView image3;
        ImageView image4;
        ImageView image5;
        ImageView image6;
        ImageView image7;
        ImageView image8;
        TextView likeCount;
        TextView replyCount;
        TextView date;


        public RvHolder(View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profilePic);
            userName = itemView.findViewById(R.id.userName);
            userWriting = itemView.findViewById(R.id.userWriting);
            userFollower = itemView.findViewById(R.id.userFollower);
            tag1 = itemView.findViewById(R.id.tag1);
            tag2 = itemView.findViewById(R.id.tag2);
            contents = itemView.findViewById(R.id.contents);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
            image4 = itemView.findViewById(R.id.image4);
            image5 = itemView.findViewById(R.id.image5);
            image6 = itemView.findViewById(R.id.image6);
            image7 = itemView.findViewById(R.id.image7);
            image8 = itemView.findViewById(R.id.image8);
            likeCount = itemView.findViewById(R.id.likeCount);
            replyCount = itemView.findViewById(R.id.replyCount);
            date = itemView.findViewById(R.id.date);
        }
    }

}
