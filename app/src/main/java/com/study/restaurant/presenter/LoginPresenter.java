package com.study.restaurant.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.model.User;
import com.study.restaurant.util.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoginPresenter {
    Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    public boolean processLogin(String s) {
        try {
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            List<User> users = new Gson().fromJson(s, listType);
            //사용자 정보 저장하기
            BananaPreference.getInstance(context).saveUser(users.get(0));
            return true;
        } catch (Exception e) {
            Logger.d(e.toString());
            return false;
        }
    }
}
