package com.study.restaurant.view;

import com.study.restaurant.model.Region;

public interface FindRestaurantView {
    void setRegion(Region region);

    void showSelectRegionPopup();

    void showSortPopup();
}
