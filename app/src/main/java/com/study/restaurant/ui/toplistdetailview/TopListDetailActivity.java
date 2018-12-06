package com.study.restaurant.ui.toplistdetailview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.TopList;
import com.study.restaurant.model.TopListDetail;
import com.study.restaurant.util.MyGlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopListDetailActivity extends AppCompatActivity {

    RecyclerView rvTopDetailList;
    ArrayList<TopListDetail> detailListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_list_detail);

        rvTopDetailList = findViewById(R.id.rvTopDetailList);
        TopListDetailAdapter topListDetailAdapter = new TopListDetailAdapter();
        rvTopDetailList.setAdapter(topListDetailAdapter);
        rvTopDetailList.setLayoutManager(new LinearLayoutManager(this));
        Map<String, String> param = new HashMap<>();
        param.put("top_list_id", getTopList().getTop_list_id());
        ApiManager.getInstance().getToplistDetail(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {

                Type type = new TypeToken<ArrayList<TopListDetail>>() {
                }.getType();
                detailListItems = new Gson().fromJson(result, type);
                topListDetailAdapter.setDetailListItems(detailListItems);

            }

            @Override
            public void failed(String msg) {

            }
        });
        rvTopDetailList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if ((int) view.getTag() == 0) {

                } else if ((int) view.getTag() == 1) {
                    outRect.top = 20;
                    outRect.left = 20;
                    outRect.right = 20;
                    outRect.bottom = 20;
                } else {
                    outRect.left = 20;
                    outRect.right = 20;
                    outRect.bottom = 20;
                }
            }
        });
    }

    private TopList getTopList() {
        return getIntent().getParcelableExtra("topList");
    }

    public static void go(Context context, TopList topList) {
        Intent intent = new Intent(context, TopListDetailActivity.class);
        intent.putExtra("topList", topList);
        context.startActivity(intent);
    }

    private class TopListDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<TopListDetail> detailListItems;

        public void setDetailListItems(ArrayList<TopListDetail> detailListItems) {
            this.detailListItems = detailListItems;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (TopDetailListItem.TopDetailListItemType.values()[viewType]
                    == TopDetailListItem.TopDetailListItemType.TOP_LIST_DETAIL_TITLE) {
                return new TopListTitleVH(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_top_list_detail_title, parent, false));
            } else {
                return new TopListTitleVH(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_top_list1, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            holder.itemView.setTag(position);
            if (position == 0) {

            } else {

                ((TextView) holder.itemView.findViewById(R.id.title)).setText(detailListItems.get(position - 1).getStore_name());
                ((TextView) holder.itemView.findViewById(R.id.score)).setText(detailListItems.get(position - 1).getScore());
                ((TextView) holder.itemView.findViewById(R.id.reply_contents)).setText(detailListItems.get(position - 1).getReply_contents());
                ((TextView) holder.itemView.findViewById(R.id.address)).setText(detailListItems.get(position - 1).getAddress());

                MyGlide.with(holder.itemView.getContext())
                        .load(detailListItems.get(position - 1).getProfile_pic())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into((ImageView) holder.itemView.findViewById(R.id.profilePic));

                MyGlide.with(holder.itemView.getContext())
                        .load(detailListItems.get(position - 1).getProfile_pic())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into((ImageView) holder.itemView.findViewById(R.id.image));

            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0)
                return TopDetailListItem.TopDetailListItemType.TOP_LIST_DETAIL_TITLE.ordinal();
            return TopDetailListItem.TopDetailListItemType.TOP_LIST_DETAIL_ITEM.ordinal();
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (detailListItems != null)
                count = detailListItems.size() + 1;

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
    }

    public class TopListTitleVH extends RecyclerView.ViewHolder {

        public TopListTitleVH(View itemView) {
            super(itemView);
        }
    }
}
