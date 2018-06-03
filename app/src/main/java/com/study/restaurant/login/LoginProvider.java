package com.study.restaurant.login;

import com.study.restaurant.model.User;

public class LoginProvider {

    CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void onSuccessLogin(User user);
    }
}
