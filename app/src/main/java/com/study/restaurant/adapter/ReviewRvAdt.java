package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.model.News;
import com.study.restaurant.model.Review;
import com.study.restaurant.databinding.ItemNewsBinding;

import java.util.ArrayList;

/**
 * Layout {@link R.layout#item_news}
 */
public class ReviewRvAdt extends RecyclerView.Adapter<ReviewHolder> {

    private ArrayList<News> newsList;
    private ArrayList<Review> reviewList;

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNewsBinding itemNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ReviewHolder(itemNewsBinding);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.itemNewsBinding.setReview(newsList.get(position));
        News news = newsList.get(position);

        if (news.getStorePictures() != null) {
            for (int i = 0; i < news.getStorePictures().size(); i++) {
                holder.pictureLayout.addView(news.getStorePictures().get(i).getView(holder.itemView.getContext()));
            }
        }

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