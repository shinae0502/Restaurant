package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.R;
import com.study.restaurant.fragment.NewsListFragment;
import com.study.restaurant.model.News;
import com.study.restaurant.model.Review;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;

public class ReviewRvAdt extends RecyclerView.Adapter<ReviewHolder> {

    private ArrayList<News> newsList;
    private ArrayList<Review> reviewList;

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ReviewHolder rvHolder = new ReviewHolder(v);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        News news = newsList.get(position);
        MyGlide.with(holder.itemView.getContext())
                .load(news.getProfile_img())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.profilePic);

        if (!news.getImage1().equals("")) {
            holder.image1.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage1())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image1);
        }
        if (!news.getImage2().equals("")) {
            holder.image2.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage2())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image2);
        }
        if (!news.getImage3().equals("")) {
            holder.image3.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage3())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image3);
        }
        if (!news.getImage4().equals("")) {
            holder.image4.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage4())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image4);
        }
        if (!news.getImage5().equals("")) {
            holder.image5.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage5())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image5);
        }
        if (!news.getImage6().equals("")) {
            holder.image6.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage6())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image6);
        }
        if (!news.getImage7().equals("")) {
            holder.image7.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage7())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image7);
        }
        if (!news.getImage8().equals("")) {
            holder.image8.setVisibility(View.VISIBLE);
            MyGlide.with(holder.itemView.getContext())
                    .load(news.getImage8())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.image8);
        }

        if (news.getStorePictures() != null) {
            for (int i = 0; i < news.getStorePictures().size(); i++) {
                holder.pictureLayout.addView(news.getStorePictures().get(i).getView(holder.itemView.getContext()));
            }
        }


        holder.contents.setText(news.getContents());
        holder.date.setText(news.getDate());
        holder.likeCount.setText(news.getLike_count());
        holder.replyCount.setText(news.getReply_count());
        holder.tag1.setText(news.getTag1());
        holder.tag2.setText(news.getTag2());
        holder.userFollower.setText(news.getUser_follower());
        holder.userName.setText(news.getUser_name());
        holder.userWriting.setText(news.getUser_writing());
        if (Integer.valueOf(news.getScore()) <= 1) {
            holder.imgScroe.setImageResource(R.drawable.ic_egmt_review_rating_3_pressed);
            holder.tvScore.setText("별로");
        } else if (Integer.valueOf(news.getScore()) == 3) {
            holder.imgScroe.setImageResource(R.drawable.ic_egmt_review_rating_2_pressed);
            holder.tvScore.setText("괜찮다");
        } else if (Integer.valueOf(news.getScore()) == 5) {
            holder.imgScroe.setImageResource(R.drawable.ic_egmt_review_rating_1_pressed);
            holder.tvScore.setText("맛있다!");
        }

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

    public void setReviews(ArrayList<Review> items) {
        this.reviewList = items;
        notifyDataSetChanged();
    }
}