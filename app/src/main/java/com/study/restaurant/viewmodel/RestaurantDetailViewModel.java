package com.study.restaurant.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.view.View;

import com.study.restaurant.adapter.AroundRestaurantRvAdt;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.model.StoreDetail;
import com.study.restaurant.model.StoreSpec;

public class RestaurantDetailViewModel extends ViewModel {
    /**
     * 화면 이동 네비게이션
     */
    RestaurantDetailNavigation restaurantDetailNavigation;

    ReviewRvAdt reviewRvAdt = new ReviewRvAdt();
    TopListRvAdt topListRvAdt = new TopListRvAdt();
    StoryRvAdt storyRvAdt = new StoryRvAdt();
    AroundRestaurantRvAdt aroundRestaurantRvAdt = new AroundRestaurantRvAdt();

    ObservableField<StoreDetail> storeDetailObservableField = new ObservableField<>();
    public MutableLiveData<Boolean> isExistsFavoriteId = new MutableLiveData<>();

    public RestaurantDetailViewModel() {
        isExistsFavoriteId.setValue(false);
    }

    public ObservableField<StoreDetail> getStoreDetailObservableField() {
        return storeDetailObservableField;
    }

    public StoreDetail getStoreDetail() {
        return storeDetailObservableField.get();
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        storeDetailObservableField.set(storeDetail);
        storeDetailObservableField.notifyChange();
        isExistsFavoriteId.setValue(storeDetail.getRestaurant().isExistsFavority_id());
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

    public void clickImage(View v) {
        restaurantDetailNavigation.goDetailPhoto();
    }

    public void clickCheckIn(View v) {
        restaurantDetailNavigation.goCheckIn();
    }

    public interface RestaurantDetailNavigation {
        void goDetailPhoto();

        void goCheckIn();
    }
}
