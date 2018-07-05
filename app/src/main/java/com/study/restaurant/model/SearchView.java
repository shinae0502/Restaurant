package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.study.restaurant.BR;
import com.study.restaurant.util.LOG;

import java.util.ArrayList;

public class SearchView extends BaseObservable {
    ArrayList<Search> searchList = new ArrayList<>();
    ArrayList<Search> recommandKeyword = new ArrayList<>();
    ArrayList<Search> recentKeyword = new ArrayList<>();

    String keyword;
    private int currentPosition;

    @Bindable
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        notifyPropertyChanged(BR.keyword);
        notifyPropertyChanged(BR.emptyKeyword);
    }

    @Bindable
    public boolean isEmptyKeyword() {

        return TextUtils.isEmpty(keyword) || currentPosition == 2;
    }

    public ArrayList<Search> getRecentKeyword() {
        return recentKeyword;
    }

    public void setRecentKeyword(ArrayList<Search> recentKeyword) {
        this.recentKeyword = recentKeyword;
    }

    public SearchView() {
        String[] strings = {"아메리칸버거위크", "망플할인", "쿠폰있는식당", "줄서는맛집", "족발"};
        for (int i = 0; i < strings.length; i++) {
            Search search = new Search();
            search.setParent(this);
            search.keyword = strings[i];
            recommandKeyword.add(search);
        }

        String[] strings1 = {"가가", "나나", "다다", "라라", "마마"};
        for (int i = 0; i < strings1.length; i++) {
            Search search = new Search();
            search.setParent(this);
            search.keyword = strings1[i];
            recentKeyword.add(search);
        }

    }


    @Bindable
    public ArrayList<Search> getRecommandKeyword() {
        return recommandKeyword;
    }

    public void setRecommandKeyword(ArrayList<Search> recommandKeyword) {
        this.recommandKeyword = recommandKeyword;
    }

    @Bindable
    public ArrayList<Search> getSearchList() {
        return searchList;
    }

    public void setSearchList(ArrayList<Search> searchList) {
        this.searchList = searchList;
    }

    @Bindable
    public TextWatcher getTextWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                LOG.d(charSequence);
                //TODO::검색어 API CALL 하기
                requestRecommandKeyword(charSequence.toString());
                setKeyword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    private void requestRecommandKeyword(String s) {
        searchList.removeAll(searchList);
        if (s.equals("a")) {
            for (int i = 0; i < 10; i++) {
                Search search = new Search();
                search.keyword = "a";
                searchList.add(search);
            }
        } else if (s.equals("s")) {
            for (int i = 0; i < 10; i++) {
                Search search = new Search();
                search.keyword = "s";
                searchList.add(search);
            }
        } else if (s.equals("d")) {
            for (int i = 0; i < 10; i++) {
                Search search = new Search();
                search.keyword = "d";
                searchList.add(search);
            }
        } else if (s.equals("f")) {
            for (int i = 0; i < 10; i++) {
                Search search = new Search();
                search.keyword = "f";
                searchList.add(search);
            }
        }

        notifyPropertyChanged(BR.searchList);
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
