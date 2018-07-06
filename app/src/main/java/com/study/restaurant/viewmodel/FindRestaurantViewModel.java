package com.study.restaurant.viewmodel;

import com.study.restaurant.adapter.StoreRvAdt;

public class FindRestaurantViewModel {
    StoreRvAdt storeRvAdt = new StoreRvAdt();

    public StoreRvAdt getStoreRvAdt() {
        return storeRvAdt;
    }

    public void setStoreRvAdt(StoreRvAdt storeRvAdt) {
        this.storeRvAdt = storeRvAdt;
    }
}
