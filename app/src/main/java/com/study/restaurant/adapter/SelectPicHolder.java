package com.study.restaurant.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.restaurant.R;

public class SelectPicHolder extends RecyclerView.ViewHolder {

    public ImageView img;
    public ImageView dim;
    public TextView tag;

    public SelectPicHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        dim = itemView.findViewById(R.id.dim);
        tag = itemView.findViewById(R.id.imgtag);
    }
}
