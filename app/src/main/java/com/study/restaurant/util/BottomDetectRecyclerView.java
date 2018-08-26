package com.study.restaurant.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class BottomDetectRecyclerView extends RecyclerView {

    boolean isLast = false;

    public BottomDetectRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void addOnBottomDetectListener(OnBottomDetectListener mOnBottomDetectListener) {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && !isLast) {
                    isLast = true;
                    if (mOnBottomDetectListener != null)
                        mOnBottomDetectListener.onDetectBottom();
                }
            }
        });
    }

    public void setLast(boolean last) {
        this.isLast = last;
    }

    public interface OnBottomDetectListener {
        void onDetectBottom();
    }
}
