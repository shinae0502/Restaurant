package com.study.restaurant.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ItemImgBinding;
import com.study.restaurant.model.Image;

import java.util.ArrayList;

/**
 * {@link R.layout#item_img}
 */
public class StoreImgRvAdt extends RecyclerView.Adapter {
    ArrayList<Image> imageArrayList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreImageRvHolder(ItemImgBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StoreImageRvHolder) holder).itemImgBinding.setImage(imageArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (imageArrayList != null)
            count = imageArrayList.size();
        return count;
    }

    public void setImageArrayList(ArrayList<Image> imageArrayList) {
        this.imageArrayList = imageArrayList;
        notifyDataSetChanged();
    }
}
