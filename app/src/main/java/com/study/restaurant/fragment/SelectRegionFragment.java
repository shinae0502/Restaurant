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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Region;
import com.study.restaurant.presenter.SelectRegionPopupPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectRegionFragment extends Fragment {


    private ArrayList<Region> regionList;
    private SelectRegionPopupPresenter presenter;
    private City city;
    private int mPosition;
    private String cityName;

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
        SelectRegionRvAdapter selectRegionRvAdapter = new SelectRegionRvAdapter();

        recyclerView.setAdapter(selectRegionRvAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(25));
        return v;
    }


    public void setPresenter(SelectRegionPopupPresenter presenter) {
        this.presenter = presenter;
    }


    public void setPosition(int position) {
        mPosition = position;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
            holder.name.setText(presenter.getCities().getCity(cityName).getRegions().get(position).getRegion_name());
            holder.region = presenter.getCities().getCity(cityName).getRegions().get(position);
        }

        @Override
        public int getItemCount() {
            int count = presenter.getCities().getCity(cityName).getRegions().size();
            return count;
        }
    }

    private class SelectRegionViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        RelativeLayout regionLayout;
        Region region;

        public void setRegion(Region region) {
            this.region = region;
        }

        public SelectRegionViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            regionLayout =itemView.findViewById(R.id.regionLayout);
            regionLayout.setOnClickListener(view ->
                    {
                        region.isSelected = !region.isSelected;
                        regionLayout.setSelected(region.isSelected);
                        presenter.validateButton();
                    });
            name.setOnClickListener(view ->
                {
                    region.isSelected = !region.isSelected;
                    regionLayout.setSelected(region.isSelected);
                    presenter.validateButton();
                });
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

            /*// Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }*/
        }
    }
}
