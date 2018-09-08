package com.study.restaurant.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

import java.util.ArrayList;

public class TopListDetailActivity extends AppCompatActivity {

    RecyclerView rvTopDetailList;
    ArrayList<TopDetailListItem> detailListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list_detail);

        rvTopDetailList = findViewById(R.id.rvTopDetailList);
        TopListDetailAdapter topListDetailAdapter = new TopListDetailAdapter();
        rvTopDetailList.setAdapter(topListDetailAdapter);
        rvTopDetailList.setLayoutManager(new LinearLayoutManager(this));
        makeDummy();

        topListDetailAdapter.setDetailListItems(detailListItems);
    }

    private void makeDummy() {
        TopDetailListItem topDetailListItem = new TopDetailListItem();
        topDetailListItem.itemType = TopDetailListItem.TopDetailListItemType.TOP_LIST_DETAIL_TITLE.ordinal();
        topDetailListItem.title = "수제버거 맛집 베스트 30곳";
        topDetailListItem.hit = "380,029";
        topDetailListItem.date = "2018-09-04_05:13:00";
        topDetailListItem.subtitle = "버거덕후라면 꼭 방문해야할 이곳들";
        detailListItems.add(topDetailListItem);
    }

    public static void go(Context context) {
        Intent intent = new Intent(context, TopListDetailActivity.class);
        context.startActivity(intent);
    }

    private class TopListDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<TopDetailListItem> detailListItems;

        public void setDetailListItems(ArrayList<TopDetailListItem> detailListItems) {
            this.detailListItems = detailListItems;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (TopDetailListItem.TopDetailListItemType.values()[viewType] == TopDetailListItem.TopDetailListItemType.TOP_LIST_DETAIL_TITLE) {
                return new TopListTitleVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_list_detail_title, parent, false));
            }

            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            return detailListItems.get(position).itemType;
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (detailListItems != null)
                count = detailListItems.size();

            return count;
        }
    }

    public static class TopDetailListItem {
        enum TopDetailListItemType {
            TOP_LIST_DETAIL_TITLE,
            TOP_LIST_DETAIL_ITEM,
            TOP_LIST_SEE_MORE_DETAIL,
            ANOTHER_RESTAURANT_LIST,
            ANOTHER_RESTAURANT_LIST_ITEM,
            POPULATE_STORY,
            RELATIVE_RESTAURANT
        }

        int itemType;

        String title;
        String hit;
        String date;
        String subtitle;
        String isBookMark;
    }

    public class TopListTitleVH extends RecyclerView.ViewHolder {

        public TopListTitleVH(View itemView) {
            super(itemView);
        }
    }
}
