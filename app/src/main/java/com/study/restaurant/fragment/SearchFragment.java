package com.study.restaurant.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.databinding.FragmentSearchBinding;
import com.study.restaurant.databinding.ItemSearchBinding;
import com.study.restaurant.model.SearchView;
import com.study.restaurant.util.VerticalSpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    public enum Type {
        RECOMMAND,
        RECENT
    }

    Type type;

    private SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }

    FragmentSearchBinding fragmentSearchBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        fragmentSearchBinding.searchRv.setAdapter(new SearchRvAdt());
        fragmentSearchBinding.searchRv.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentSearchBinding.searchRv.addItemDecoration(new VerticalSpaceItemDecoration(25));

        return fragmentSearchBinding.getRoot();
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;

        /*searchView.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (fragmentSearchBinding != null)
                    if (fragmentSearchBinding.searchRv.getAdapter() != null)
                        fragmentSearchBinding.searchRv.getAdapter().notifyDataSetChanged();
            }
        });*/
    }

    public class SearchRvAdt extends RecyclerView.Adapter<SearchHolder> {


        @Override
        public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return SearchHolder.create(parent, viewType);
        }

        @Override
        public void onBindViewHolder(SearchHolder holder, int position) {
            if (type == Type.RECOMMAND)
                holder.itemSearchBinding.setSearch(searchView.getRecommandKeyword().get(position));
            else if (type == Type.RECENT)
                holder.itemSearchBinding.setSearch(searchView.getRecentKeyword().get(position));
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (searchView != null) {
                if (type == Type.RECOMMAND)
                    count = searchView.getRecommandKeyword().size();
                else if (type == Type.RECENT)
                    count = searchView.getRecentKeyword().size();
            }
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
