package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ItemReviewBinding;

public class ReviewHolder extends RecyclerView.ViewHolder {
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

    ImageView imgScroe;
    TextView tvScore;
    LinearLayout pictureLayout;


    public ReviewHolder(View itemView) {
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
        imgScroe = itemView.findViewById(R.id.imgScore);
        tvScore = itemView.findViewById(R.id.tvScore);
        pictureLayout = itemView.findViewById(R.id.pictureLayout);
    }
}
