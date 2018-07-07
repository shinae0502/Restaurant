package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.study.restaurant.model.Review;

import java.util.ArrayList;

public class ReviewRvAdt extends RecyclerView.Adapter<ReviewHolder> {

    private ArrayList<Review> reviews;

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReviewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.itemReviewBinding.setReview(reviews.get(position));
            /*MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.img);*/
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (reviews != null)
            count = reviews.size();

        return count;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }
}