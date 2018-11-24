package com.study.restaurant.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.study.restaurant.model.User;
import com.study.restaurant.util.Logger;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookLoginProvider extends LoginProvider {

    private static FacebookLoginProvider facebookLoginProvider;
    private CallbackManager callbackManager; //로그인 상태를
    private Activity activity;
    private OnResultLoginListener onResultLoginListener;
    //콜백 리스너

    public static FacebookLoginProvider getInstance(Activity activity) {
        if (facebookLoginProvider == null)
            synchronized (FacebookLoginProvider.class) {
                if (facebookLoginProvider == null)
                    facebookLoginProvider = new FacebookLoginProvider(activity);
            }
        if (activity != null && !facebookLoginProvider.activity.equals(activity))
            facebookLoginProvider.activity = activity;
        return facebookLoginProvider;
    }

    public FacebookLoginProvider(Activity activity) {
        this.activity = activity;
    }

    public void onCreate() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                if (onResultLoginListener != null) {
                    User user = new User();
                    user.accessToken = loginResult.getAccessToken().getToken();
                    onResultLoginListener.onResult(0, user);
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(activity, "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Logger.e("onError" + error.toString());
                Toast.makeText(activity, "onError" + error.toString(), Toast.LENGTH_SHORT).show();
                LoginManager.getInstance().logOut();
            }
        });
    }

    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            Toast.makeText(activity, "callbackManager is null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void requestLogin(OnResultLoginListener onResultLoginListener) {
        this.onResultLoginListener = onResultLoginListener;
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
    }

    @Override
    public void requestUser(OnReceiveUserListener onReceiveUserListener) {
//        if (isLoggedIn()) {
//            callbackManager = CallbackManager.Factory.create();
//            AccessToken accessToken = AccessToken.getCurrentAccessToken();
//            Logger.v(accessToken);
//            if (accessToken != null) {
//                if (!accessToken.isExpired()) {
//                    GraphRequest request = GraphRequest.newMeRequest(accessToken, (object, response) -> {
//                        Logger.v(object.toString());
//                        Toast.makeText(activity, object.toString(), Toast.LENGTH_SHORT).show();
//                        User user = new Gson().fromJson(object.toString(), User.class);
//                        onReceiveUserListener.onReceive(user);
//                    });
//
//                    Bundle parameters = new Bundle();
//                    parameters.putString("fields", "user_id,name,email,gender,birthday");
//                    request.setParameters(parameters);
//                    request.executeAsync();
//                }
//            }
//        }
    }

    @Override
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    public void logout(OnResultLogoutListener onResultLogoutListener) {
        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    //write your code here what to do when user logout
                    if (onResultLogoutListener != null) {
                        onResultLogoutListener.onResult(0);
                    }
                }
            }
        };

        LoginManager.getInstance().logOut();
    }

    public void onDestory() {

    }
}
