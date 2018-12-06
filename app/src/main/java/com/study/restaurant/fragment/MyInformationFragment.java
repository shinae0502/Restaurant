package com.study.restaurant.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.ui.settingview.SettingActivity;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.databinding.FragmentMyinformationBinding;
import com.study.restaurant.model.User;
import com.study.restaurant.util.MyGlide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyInformationFragment extends Fragment {

    FragmentMyinformationBinding fragmentMyinformationBinding;

    public MyInformationFragment() {
        // Required empty public constructor
    }

    public static MyInformationFragment newInstance() {
        MyInformationFragment fragment = new MyInformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMyinformationBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_myinformation, null, false);

        requestUserInformation();

        fragmentMyinformationBinding.setting.setOnClickListener(view -> SettingActivity.go((AppCompatActivity) getActivity()));


        return fragmentMyinformationBinding.getRoot();
    }

    private void requestUserInformation() {
        if (!BananaPreference.getInstance(getActivity()).loadUser().isLogin())
            return;

        Map<String, String> param = new HashMap<>();
        param.put("user_id", BananaPreference.getInstance(getActivity()).loadUser().user_id);
        ApiManager.getInstance().getUser(param, new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                Type type = new TypeToken<ArrayList<User>>() {
                }.getType();
                ArrayList<User> userArrayList = new Gson().fromJson(result, type);
                if (userArrayList.size() < 1) return;
                updateUI(userArrayList.get(0));
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    private void updateUI(User user) {
        fragmentMyinformationBinding.tvCheckIn.setText(user.checkin);
        fragmentMyinformationBinding.tvFollower.setText(user.follower);
        fragmentMyinformationBinding.tvFollowing.setText(user.following);
        fragmentMyinformationBinding.tvPicture.setText(user.upload_pictures);
        fragmentMyinformationBinding.tvReview.setText(user.review);

        MyGlide.with(getContext())
                .load(user.picture)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(fragmentMyinformationBinding.myImg);

        fragmentMyinformationBinding.tvName.setText(user.name);


    }
}
