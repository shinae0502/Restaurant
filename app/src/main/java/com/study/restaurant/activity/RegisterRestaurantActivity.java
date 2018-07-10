package com.study.restaurant.activity;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityRegisterRestaurantBinding;
import com.study.restaurant.view.RegisterRestaurantNavigator;
import com.study.restaurant.viewmodel.RegisterRestaurantViewModel;

public class RegisterRestaurantActivity extends AppCompatActivity implements RegisterRestaurantNavigator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterRestaurantBinding activityRegisterRestaurantBindin = DataBindingUtil.setContentView(this, R.layout.activity_register_restaurant);
        activityRegisterRestaurantBindin.setVm(new RegisterRestaurantViewModel(this));
    }

    @BindingAdapter({"app:selected"})
    public static void selectedBind(ViewGroup viewGroup
            , boolean selected
    ) {
        viewGroup.setSelected(selected);
    }

    @BindingAdapter({"app:textWhatcher"})
    public static void setTextWhatcher(EditText editText, TextWatcher textWatcher)
    {
        editText.addTextChangedListener(textWatcher);
    }

    public static void go(AppCompatActivity appCompatActivity)
    {
        appCompatActivity.startActivity(new Intent(appCompatActivity, RegisterRestaurantActivity.class));
    }

    public void clickBackBtn(View v)
    {
        onBackPressed();
    }
}
