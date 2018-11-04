package com.study.restaurant.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.study.restaurant.model.User;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookLoginProvider extends LoginProvider {

    private static FacebookLoginProvider facebookLoginProvider;
    private CallbackManager callbackManager; //로그인 상태를
    private Activity activity;
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
                if (callBack != null) {
                    User user = new User();
                    user.accessToken = loginResult.getAccessToken().getToken();
                    callBack.onSuccessLogin(user);
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(activity, "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
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

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public void requestLogin() {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
    }

    private void requestProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("exceptiontag", object.toString());
                Toast.makeText(activity, object.toString(), Toast.LENGTH_SHORT).show();
                User user = new Gson().fromJson(object.toString(), User.class);
                callBack.onSuccessLogin(user);
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "user_id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    /**
     * 엑세스 토큰이 없을경우 현재꺼를 사용 함.
     */
    public void requestProfile() {
        if (isLoggedIn()) {
            callbackManager = CallbackManager.Factory.create();
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken != null) {
                if (!accessToken.isExpired()) {
                    requestProfile(accessToken);
                }
            }
        }
    }

    public void logout() {
        LoginManager.getInstance().logOut();
    }
}
