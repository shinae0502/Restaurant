package com.study.restaurant.view;

import com.study.restaurant.model.Store;

public interface FindRestaurantNavigation {

    void showSelectRegionPopup();

    void showSortPopup();

    void showBoundaryPopup();

    void showFilterPopup();

    void goSearch();

    void goDeatilRestaurant(Store store);
}
