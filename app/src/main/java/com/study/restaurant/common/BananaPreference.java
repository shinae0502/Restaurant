package com.study.restaurant.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.study.restaurant.model.User;
import com.study.restaurant.util.LOG;

public class BananaPreference {
    private static BananaPreference bananaPreference;

    public static BananaPreference getInstance(Context context) {
        if (bananaPreference == null)
            bananaPreference = new BananaPreference(context);

        return bananaPreference;
    }

    SharedPreferences sharedPreferences;

    BananaPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("banana", Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        LOG.d(user.toString());
        sharedPreferences.edit().putString("user", new Gson().toJson(user)).commit();
    }

    public User loadUser() {
        String user = sharedPreferences.getString("user", "");
        LOG.d(user);
        return new Gson().fromJson(user, User.class);
    }
}
