package com.study.restaurant.viewmodel;

import android.view.View;

import com.study.restaurant.adapter.AroundRestaurantRvAdt;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.model.StoreSpec;

public class RestaurantDetailViewModel {
    private StoreSpec storeSpec;

    /**
     * 화면 이동 네비게이션
     */
    RestaurantDetailNavigation restaurantDetailNavigation;

    ReviewRvAdt reviewRvAdt = new ReviewRvAdt();
    TopListRvAdt topListRvAdt = new TopListRvAdt();
    StoryRvAdt storyRvAdt = new StoryRvAdt();
    AroundRestaurantRvAdt aroundRestaurantRvAdt = new AroundRestaurantRvAdt();

    public StoreSpec getStoreSpec() {
        return storeSpec;
    }

    public ReviewRvAdt getReviewRvAdt() {
        return reviewRvAdt;
    }

    public void setReviewRvAdt(ReviewRvAdt reviewRvAdt) {
        this.reviewRvAdt = reviewRvAdt;
    }

    public TopListRvAdt getTopListRvAdt() {
        return topListRvAdt;
    }

    public void setTopListRvAdt(TopListRvAdt topListRvAdt) {
        this.topListRvAdt = topListRvAdt;
    }

    public StoryRvAdt getStoryRvAdt() {
        return storyRvAdt;
    }

    public void setStoryRvAdt(StoryRvAdt storyRvAdt) {
        this.storyRvAdt = storyRvAdt;
    }

    public AroundRestaurantRvAdt getAroundRestaurantRvAdt() {
        return aroundRestaurantRvAdt;
    }

    public void setAroundRestaurantRvAdt(AroundRestaurantRvAdt aroundRestaurantRvAdt) {
        this.aroundRestaurantRvAdt = aroundRestaurantRvAdt;
    }

    public RestaurantDetailNavigation getRestaurantDetailNavigation() {
        return restaurantDetailNavigation;
    }

    public void setRestaurantDetailNavigation(RestaurantDetailNavigation restaurantDetailNavigation) {
        this.restaurantDetailNavigation = restaurantDetailNavigation;
    }

    public void setStoreSpec(StoreSpec storeSpec) {
        this.storeSpec = storeSpec;
    }

    public void clickImage(View v) {
        restaurantDetailNavigation.goDetailPhoto();
    }

    public interface RestaurantDetailNavigation {
        void goDetailPhoto();

    }
}
