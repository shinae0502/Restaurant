package com.study.restaurant.fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.study.restaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectRegionFragment extends Fragment {


    public SelectRegionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select_region, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.regionRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new SelectRegionRvAdapter());
        recyclerView.addItemDecoration(new SpacesItemDecoration(15));
        return v;
    }

    private class SelectRegionRvAdapter extends RecyclerView.Adapter<SelectRegionViewHolder> {

        @NonNull
        @Override
        public SelectRegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            SelectRegionViewHolder selectRegionViewHolder = new SelectRegionViewHolder(
                    View.inflate(parent.getContext(), R.layout.item_region, null)
            );
            return selectRegionViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SelectRegionViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private class SelectRegionViewHolder extends RecyclerView.ViewHolder {

        public SelectRegionViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
