package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.study.restaurant.util.LOG;

public class Search extends BaseObservable {

    SearchView parent;
    String keyword;

    public void setParent(SearchView parent) {
        this.parent = parent;
    }

    public void click(View v) {
        parent.setKeyword(keyword);
    }

    @Bindable
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
