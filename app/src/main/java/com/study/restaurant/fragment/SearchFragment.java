package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentFindRestaurantBinding;
import com.study.restaurant.databinding.FragmentSearchBinding;
import com.study.restaurant.databinding.ItemBinding;
import com.study.restaurant.databinding.ItemSearchBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.util.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSearchBinding fragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        fragmentSearchBinding.searchRv.setAdapter(new SearchRvAdt());
        fragmentSearchBinding.searchRv.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentSearchBinding.searchRv.addItemDecoration(new VerticalSpaceItemDecoration(25));

        return fragmentSearchBinding.getRoot();
    }

    public class SearchRvAdt extends RecyclerView.Adapter<SearchHolder> {


        @Override
        public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return SearchHolder.create(parent, viewType);
        }

        @Override
        public void onBindViewHolder(SearchHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            int count = 10;
            return count;
        }
    }

    public static class SearchHolder extends RecyclerView.ViewHolder {
        ItemSearchBinding itemSearchBinding;

        public static SearchHolder create(ViewGroup parent, int viewType) {
            ItemSearchBinding itemSearchBinding = ItemSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SearchHolder(itemSearchBinding);
        }


        public SearchHolder(ItemSearchBinding itemSearchBinding) {
            super(itemSearchBinding.getRoot());
            this.itemSearchBinding = itemSearchBinding;
        }
    }

}
