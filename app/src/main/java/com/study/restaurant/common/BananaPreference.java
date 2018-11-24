package com.study.restaurant.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.study.restaurant.model.User;
import com.study.restaurant.util.Logger;

import java.util.UUID;

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
        Logger.d(user.toString());
        sharedPreferences.edit().putString("user", new Gson().toJson(user)).apply();
    }

    public User loadUser() {
        String user = sharedPreferences.getString("user", "");
        Logger.d(user);
        return new Gson().fromJson(user, User.class);
    }


    public String getUUID() {
        if (sharedPreferences.getString("UUID", "").equals("")) {
            String uuid = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("UUID", uuid).apply();
            return uuid;
        } else {
            return sharedPreferences.getString("UUID", "");
        }
    }

    public String getAccessToken() {
        String accessToken = "";
        if (loadUser() != null && loadUser().accessToken != null)
            accessToken = loadUser().accessToken;
        return accessToken;
    }
}
