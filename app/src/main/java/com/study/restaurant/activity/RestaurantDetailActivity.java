package com.study.restaurant.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityRestaurantDetailBinding;
import com.study.restaurant.databinding.ItemReviewBinding;
import com.study.restaurant.fragment.FindRestaurantFragment;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreSpec;
import com.study.restaurant.util.LOG;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailActivity extends AppCompatActivity {

    ActivityRestaurantDetailBinding activityRestaurantDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);
        activityRestaurantDetailBinding.setStore(getStore());
        AppBarLayout appBarLayout = activityRestaurantDetailBinding.appBar;
        final Toolbar actionBar = activityRestaurantDetailBinding.actionBar;
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

        //레스토랑 상세 정보 불러오기
        String dummy = "{\"img1\":\"http://www.globalcardsalud.com/wp-content/uploads/2011/12/banquete.jpg\"," +
                "\"img2\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVjQ08rDw0pOvd8q7Un6KByZ1GrFMAKGb89JTT1pZQlQVpiSEC\"" +
                ",\"img3\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/220px-Good_Food_Display_-_NCI_Visuals_Online.jpg\"" +
                ",\"img4\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5koo3US24I9QEb-Su1ZUz9nVFW9-10IZ_V7QtDKxMUsARUTIv\"" +
                ",\"img5\":\"https://steptohealth.co.kr/wp-content/uploads/2017/03/foods-to-avoid-eating-for-breakfast-500x283.jpg\"" +
                ",\"updateDate\":\"2018-07-03\"" +
                ",\"openingHours\":\"월-금: 11:10 - 21:30\n     토: 11:10- 15:30\"" +
                ",\"breaktime\":\"15:00-17:00\"" +
                ",\"prices\":\"만원-2만원 / 1인\"" +
                ",\"menu1\":\"국밥\"" +
                ",\"menu2\":\"정식\"" +
                ",\"menu3\":\"술국\"" +
                ",\"menu1_price\":\"7,000\"" +
                ",\"menu2_price\":\"11,000\"" +
                ",\"menu3_price\":\"13,000\"" +
                ",\"keyword\":\"강남역,에디터,국밥\"" + "}";

        StoreSpec storeSpec = new Gson().fromJson(dummy, StoreSpec.class);

        MyGlide.with(this)
                .load(storeSpec.getImg1())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.img1);

        MyGlide.with(this)
                .load(storeSpec.getImg2())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.img2);

        MyGlide.with(this)
                .load(storeSpec.getImg3())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.img3);

        MyGlide.with(this)
                .load(storeSpec.getImg4())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.img4);

        MyGlide.with(this)
                .load(storeSpec.getImg5())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(activityRestaurantDetailBinding.img5);

        activityRestaurantDetailBinding.restaurantDetailContent.setStoreSpec(storeSpec);
        activityRestaurantDetailBinding.restaurantDetailContent.layoutRestaurantInformation.setStoreSpec(storeSpec);


        //맛깔나는 리뷰
        activityRestaurantDetailBinding.restaurantDetailContent.layoutReviewList.reviewRv.setAdapter(new RvAdt());
        activityRestaurantDetailBinding.restaurantDetailContent.layoutReviewList.reviewRv.setLayoutManager(new LinearLayoutManager(this));
        activityRestaurantDetailBinding.restaurantDetailContent.layoutReviewList.reviewRv.setNestedScrollingEnabled(false);
        //activityRestaurantDetailBinding.restaurantDetailContent.nestedScrollView
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

    public abstract static class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

        public enum State {
            EXPANDED,
            COLLAPSED,
            IDLE
        }

        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    }

    public class RvAdt extends RecyclerView.Adapter<RvHolder> {

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return RvHolder.create(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {
            /*MyGlide.with(holder.itemView.getContext())
                    .load(storeList.get(position).getImg())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.img);*/
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

    public static class RvHolder extends RecyclerView.ViewHolder {
        ItemReviewBinding itemReviewBinding;

        public static RvHolder create(ViewGroup parent, int viewType) {
            ItemReviewBinding itemReviewBinding = ItemReviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new RvHolder(itemReviewBinding);
        }

        public RvHolder(ItemReviewBinding itemReviewBinding) {
            super(itemReviewBinding.getRoot());
            this.itemReviewBinding = itemReviewBinding;
        }

        public ItemReviewBinding getItemReviewBinding() {
            return itemReviewBinding;
        }

    }
}
