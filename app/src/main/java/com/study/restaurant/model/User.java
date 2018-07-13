package com.study.restaurant.model;

import android.text.TextUtils;

import com.study.restaurant.common.BananaPreference;

public class User {
    public String id;
    public String email;
    public String login_platform;
    public String picture;
    public String name;
    public String accessToken;

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("email = " + email);
        stringBuffer.append("\n");
        stringBuffer.append("login_platform = " + login_platform);
        stringBuffer.append("\n");
        stringBuffer.append("picture = " + picture);
        stringBuffer.append("\n");
        stringBuffer.append("name = " + name);

        return stringBuffer.toString();
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(login_platform)) {
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        return true;
    }
}
