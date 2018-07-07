package com.study.restaurant.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.study.restaurant.databinding.ItemBinding;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreSpec;
import com.study.restaurant.util.AppBarStateChangeListener;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity {

    //레스토랑 상세 정보 불러오기
    ActivityRestaurantDetailBinding activityRestaurantDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create view binding
        activityRestaurantDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);

        // test create storeSpec
        StoreSpec storeSpec = new Gson().fromJson(dummy1, StoreSpec.class);

        // data binding
        activityRestaurantDetailBinding.layoutDetailRestaurantTitleBar.setStoreSpec(storeSpec);
        activityRestaurantDetailBinding.layoutDetailRestaurantTitleBar.setStore(getStore());
        activityRestaurantDetailBinding.layoutDetailRestaurantMain.setStore(getStore());
        activityRestaurantDetailBinding.setStore(getStore());

        // Set actionbar
        AppBarLayout appBarLayout = activityRestaurantDetailBinding.appBar;
        final Toolbar actionBar = activityRestaurantDetailBinding.layoutDetailRestaurantTitleBar.actionBar;
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.IDLE) {
                    actionBar.setSelected(false);
                    actionBar.setBackgroundColor(Color.WHITE);
//                    nestedScrollView.stopNestedScroll();
                } else if (state == State.COLLAPSED) {
                    actionBar.setSelected(true);
                    actionBar.setBackgroundColor(getResources().getColor(R.color.orange));
                }
            }
        });

        // Set main images
        MyGlide.with(this)
                .load(storeSpec.getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img1);

        MyGlide.with(this)
                .load(storeSpec.getImg2())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img2);

        MyGlide.with(this)
                .load(storeSpec.getImg3())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img3);

        MyGlide.with(this)
                .load(storeSpec.getImg4())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img4);

        MyGlide.with(this)
                .load(storeSpec.getImg5())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.layoutDetailRestaurantMain.img5);

        activityRestaurantDetailBinding.setStoreSpec(storeSpec);
        activityRestaurantDetailBinding.layoutRestaurantInformation.setStoreSpec(storeSpec);


        //맛깔나는 리뷰
        ReviewRvAdt adapter = new ReviewRvAdt();
        adapter.setReviews(storeSpec.getReviews());
        activityRestaurantDetailBinding.layoutReviewList.reviewRv.setAdapter(adapter);

        //관련 top 리스트
        TopListRvAdt toplistRvAdt = new TopListRvAdt();
        toplistRvAdt.setTopLists(storeSpec.getTopLists());
        activityRestaurantDetailBinding.layoutRelatedToplist.topListRv.setAdapter(toplistRvAdt);

        //관련 스토리
        StoryRvAdt storyRvAdt = new StoryRvAdt();
        storyRvAdt.setStoryList(storeSpec.getStories());
        activityRestaurantDetailBinding.layoutRelatedStory.storyRv.setAdapter(storyRvAdt);

        //주변 인기 식당
        StoreRvAdt rvAdt = new StoreRvAdt();
        rvAdt.setStoreList(storeSpec.getAroundRestaurant());
        activityRestaurantDetailBinding.layoutAroundRestaurant.restaurantRv.setAdapter(rvAdt);

    }

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, RestaurantDetailActivity.class);
        intent.putExtra("store", store);
        appCompatActivity.startActivity(intent);
    }

    private Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    public void clickClose(View view) {
        onBackPressed();
    }

    public class StoreRvAdt extends RecyclerView.Adapter<StoreHolder> {

        List<Store> storeList = new ArrayList<>();

        public List<Store> getStoreList() {
            return storeList;
        }

        public void setStoreList(List<Store> storeList) {
            this.storeList = storeList;
            notifyDataSetChanged();
        }

        @Override
        public StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return StoreHolder.create(parent, viewType);
        }

        @Override
        public void onBindViewHolder(StoreHolder holder, int position) {
            storeList.get(position).setPosition(position);
            holder.setStore(storeList.get(position));
            MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.img);
            holder.itemBinding.parent.setOnClickListener(view -> RestaurantDetailActivity.go(RestaurantDetailActivity.this, storeList.get(position)));
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (storeList != null)
                count = storeList.size();

            return count;
        }
    }

    public static class StoreHolder extends RecyclerView.ViewHolder {
        ItemBinding itemBinding;

        public static StoreHolder create(ViewGroup parent, int viewType) {
            ItemBinding itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new StoreHolder(itemBinding);
        }

        ImageView img;

        public StoreHolder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            img = itemView.findViewById(R.id.img);
        }

        public ItemBinding getItemBinding() {
            return itemBinding;
        }

        public void setStore(Store store) {
            itemBinding.setStore(store);
        }
    }


    String dummy1 = "{\n" +
            "  \"img1\": \"http://www.globalcardsalud.com/wp-content/uploads/2011/12/banquete.jpg\",\n" +
            "  \"img2\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVjQ08rDw0pOvd8q7Un6KByZ1GrFMAKGb89JTT1pZQlQVpiSEC\",\n" +
            "  \"img3\": \"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/220px-Good_Food_Display_-_NCI_Visuals_Online.jpg\",\n" +
            "  \"img4\": \"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5koo3US24I9QEb-Su1ZUz9nVFW9-10IZ_V7QtDKxMUsARUTIv\",\n" +
            "  \"img5\": \"https://steptohealth.co.kr/wp-content/uploads/2017/03/foods-to-avoid-eating-for-breakfast-500x283.jpg\",\n" +
            "  \"updateDate\": \"2018-07-03\",\n" +
            "  \"openingHours\": \"월-금: 11:10 - 21:30\\n       토: 11:10- 15:30\",\n" +
            "  \"breaktime\": \"15:00-17:00\",\n" +
            "  \"prices\": \"만원-2만원 / 1인\",\n" +
            "  \"menu1\": \"국밥\",\n" +
            "  \"menu2\": \"정식\",\n" +
            "  \"menu3\": \"술국\",\n" +
            "  \"menu1_price\": \"7,000\",\n" +
            "  \"menu2_price\": \"10,000\",\n" +
            "  \"menu3_price\": \"13,000\",\n" +
            "  \"keyword\": \"강남역,에디터,국밥\",\n" +
            "  \"reviews\": [\n" +
            "    {\n" +
            "      \"prifile_pic\": \"a\",\n" +
            "      \"name\": \"양사랑\",\n" +
            "      \"review_count\": \"10\",\n" +
            "      \"follower\": \"234\",\n" +
            "      \"tag\": \"농민백암왕순대\",\n" +
            "      \"review\": \"가가가가가\",\n" +
            "      \"img1\": \"1\",\n" +
            "      \"img2\": \"2\",\n" +
            "      \"img3\": \"3\",\n" +
            "      \"img4\": \"4\",\n" +
            "      \"img5\": \"5\",\n" +
            "      \"like\": \"5\",\n" +
            "      \"comment\": \"10\",\n" +
            "      \"date\": \"2018-07-03\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"prifile_pic\": \"a\",\n" +
            "      \"name\": \"자라\",\n" +
            "      \"review_count\": \"10\",\n" +
            "      \"follower\": \"234\",\n" +
            "      \"tag\": \"농민백암왕순대\",\n" +
            "      \"review\": \"가가가가가\",\n" +
            "      \"img1\": \"1\",\n" +
            "      \"img2\": \"2\",\n" +
            "      \"img3\": \"3\",\n" +
            "      \"img4\": \"4\",\n" +
            "      \"img5\": \"5\",\n" +
            "      \"like\": \"5\",\n" +
            "      \"comment\": \"10\",\n" +
            "      \"date\": \"2018-07-03\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"prifile_pic\": \"a\",\n" +
            "      \"name\": \"고고\",\n" +
            "      \"review_count\": \"10\",\n" +
            "      \"follower\": \"234\",\n" +
            "      \"tag\": \"농민백암왕순대\",\n" +
            "      \"review\": \"가가가가가\",\n" +
            "      \"img1\": \"1\",\n" +
            "      \"img2\": \"2\",\n" +
            "      \"img3\": \"3\",\n" +
            "      \"img4\": \"4\",\n" +
            "      \"img5\": \"5\",\n" +
            "      \"like\": \"5\",\n" +
            "      \"comment\": \"10\",\n" +
            "      \"date\": \"2018-07-03\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"topLists\": [\n" +
            "    {\n" +
            "      \"toplist_id\": \"1\",\n" +
            "      \"image\": \"http://\",\n" +
            "      \"hit\": \"56650\",\n" +
            "      \"date\": \"2018-03-04\",\n" +
            "      \"title\": \"망고플레이트 에디터 C양 추천 맛집 베스트\",\n" +
            "      \"subtitle\": \"망고플레이트 에디터R양이 아끼는..\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"toplist_id\": \"1\",\n" +
            "      \"image\": \"http://\",\n" +
            "      \"hit\": \"56650\",\n" +
            "      \"date\": \"2018-03-04\",\n" +
            "      \"title\": \"망고플레이트 에디터 C양 추천 맛집 베스트\",\n" +
            "      \"subtitle\": \"망고플레이트 에디터R양이 아끼는..\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"toplist_id\": \"1\",\n" +
            "      \"image\": \"http://\",\n" +
            "      \"hit\": \"56650\",\n" +
            "      \"date\": \"2018-03-04\",\n" +
            "      \"title\": \"망고플레이트 에디터 C양 추천 맛집 베스트\",\n" +
            "      \"subtitle\": \"망고플레이트 에디터R양이 아끼는..\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"stories\": [\n" +
            "    {\n" +
            "      \"story_id\": \"1\",\n" +
            "      \"image\": \"http://\",\n" +
            "      \"title\": \"망고플레이트 에디터 C양 추천 맛집 베스트\",\n" +
            "      \"subtitle\": \"망고플레이트 에디터R양이 아끼는..\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"story_id\": \"1\",\n" +
            "      \"image\": \"http://\",\n" +
            "      \"title\": \"망고플레이트 에디터 C양 추천 맛집 베스트\",\n" +
            "      \"subtitle\": \"망고플레이트 에디터R양이 아끼는..\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"aroundRestaurant\": [\n" +
            "    {\n" +
            "      \"store_id\": \"2\",\n" +
            "      \"name\": \"고에몬 강남점\",\n" +
            "      \"img\": \"https://lh5.googleusercontent.com/p/AF1QipOIuCK8Cmm0FEn4yZDSU-LRLPtNSNVXqLI26mnu=w408-h306-k-no\",\n" +
            "      \"lat\": \"37.4985798\",\n" +
            "      \"lon\": \"127.0255659\",\n" +
            "      \"hit\": \"1\",\n" +
            "      \"review_count\": \"10\",\n" +
            "      \"score\": \"3.5\",\n" +
            "      \"region_id\": \"100\",\n" +
            "      \"food_category_id\": \"2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"store_id\": \"1\",\n" +
            "      \"name\": \"딘타이펑 강남역점\",\n" +
            "      \"img\": \"https://lh5.googleusercontent.com/p/AF1QipOcvf4raK8kvhe7buplK5_2Fw7r_y0Iiw3bUpVH=w408-h229-k-no\",\n" +
            "      \"lat\": \"37.4985798\",\n" +
            "      \"lon\": \"127.0255659\",\n" +
            "      \"hit\": \"2\",\n" +
            "      \"review_count\": \"4\",\n" +
            "      \"score\": \"4.5\",\n" +
            "      \"region_id\": \"100\",\n" +
            "      \"food_category_id\": \"2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"store_id\": \"3\",\n" +
            "      \"name\": \"느린마을 양조장 강남점\",\n" +
            "      \"img\": \"https://lh5.googleusercontent.com/p/AF1QipPRfHHwnIOOJQLvC9lxMd42IHYtTtrzqsmW-H48=w408-h306-k-no\",\n" +
            "      \"lat\": \"37.4982914\",\n" +
            "      \"lon\": \"127.025206\",\n" +
            "      \"hit\": \"5\",\n" +
            "      \"review_count\": \"34\",\n" +
            "      \"score\": \"3.8\",\n" +
            "      \"region_id\": \"100\",\n" +
            "      \"food_category_id\": \"7\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"store_id\": \"4\",\n" +
            "      \"name\": \"장꼬방\",\n" +
            "      \"img\": \"https://lh5.googleusercontent.com/p/AF1QipOgCgZf7qJdzvACnRj0SB3pfW8TrutdPfuIjqCq=w408-h229-k-no\",\n" +
            "      \"lat\": \"37.4983037\",\n" +
            "      \"lon\": \"127.0230639\",\n" +
            "      \"hit\": \"15\",\n" +
            "      \"review_count\": \"22\",\n" +
            "      \"score\": \"4.4\",\n" +
            "      \"region_id\": \"100\",\n" +
            "      \"food_category_id\": \"0\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

}
