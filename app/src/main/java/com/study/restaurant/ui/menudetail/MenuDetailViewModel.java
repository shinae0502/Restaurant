package com.study.restaurant.ui.menudetail;

import android.arch.lifecycle.ViewModel;
import android.view.ViewGroup;

import com.study.restaurant.model.StoreMenu;

import java.util.ArrayList;

public class MenuDetailViewModel extends ViewModel {
    String title;

    // TODO: Implement the ViewModel
    public void attachMenu(ViewGroup viewGroup, ViewGroup menuImageLayout, ArrayList<StoreMenu> menus) {
        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getStoreMenuType() == StoreMenu.StoreMenuType.TEXT) {
                viewGroup.addView(menus.get(i).getView(viewGroup.getContext()));
            } else {
                menuImageLayout.addView(menus.get(i).getView(viewGroup.getContext()));
            }
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void clickProvideIncorrect() {

    }
}
