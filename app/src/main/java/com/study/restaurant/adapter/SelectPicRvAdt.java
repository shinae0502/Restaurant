package com.study.restaurant.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.R;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;

public class SelectPicRvAdt extends RecyclerView.Adapter {
    private ArrayList<MyImage> myImageList;
    private MutableLiveData<ArrayList<MyImage>> selectedImgList;
    boolean isCheckIn = false;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectPicHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_select, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SelectPicHolder selectPicHolder = (SelectPicHolder) holder;
        MyGlide.with(holder.itemView.getContext())
                .load(myImageList.get(position).getData())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(selectPicHolder.img);

        if (selectedImgList.getValue().contains(myImageList.get(position))) {
            selectPicHolder.dim.setVisibility(View.VISIBLE);
            selectPicHolder.tag.setVisibility(View.VISIBLE);
            selectPicHolder.tag.setText("" + selectedImgList.getValue().indexOf(myImageList.get(position)));
        } else {
            selectPicHolder.dim.setVisibility(View.GONE);
            selectPicHolder.tag.setVisibility(View.GONE);
            selectPicHolder.tag.setText("");
        }

        ((SelectPicHolder) holder).img.setOnClickListener((View view) -> {

            boolean isContained = selectedImgList.getValue().contains(myImageList.get(position));
            // 이미지를 선택했을때 선택 여부에 따라 딤과 카운트 보여지는 여부
            selectPicHolder.dim.setVisibility(isContained ? View.GONE : View.VISIBLE);
            selectPicHolder.tag.setVisibility(isContained ? View.GONE : View.VISIBLE);

            if (isContained) {
                selectedImgList.getValue().remove(myImageList.get(position));
                notifyDataSetChanged();
            } else {
                if (isCheckIn) {
                    selectedImgList.getValue().removeAll(selectedImgList.getValue());
                    notifyDataSetChanged();
                }
                selectedImgList.getValue().add(myImageList.get(position));
                selectedImgList.setValue(selectedImgList.getValue());
                selectPicHolder.tag.setText("" + (selectedImgList.getValue().size() - 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (myImageList != null)
            count = myImageList.size();
        return count;
    }

    public void setMyImageList(ArrayList<MyImage> myImageList) {
        this.myImageList = myImageList;
        notifyDataSetChanged();
    }

    public void setSelectedImageList
            (MutableLiveData<ArrayList<MyImage>> selectedImgList) {
        this.selectedImgList = selectedImgList;
    }

    public void setIsCheckIn(boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }
}
