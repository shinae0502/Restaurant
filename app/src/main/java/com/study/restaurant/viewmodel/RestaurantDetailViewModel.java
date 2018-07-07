package com.study.restaurant.viewmodel;

import com.study.restaurant.adapter.AroundRestaurantRvAdt;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.model.StoreSpec;

public class RestaurantDetailViewModel {
    private StoreSpec storeSpec;

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

    public void setStoreSpec(StoreSpec storeSpec) {
        this.storeSpec = storeSpec;
    }
}
