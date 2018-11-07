package com.study.restaurant.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityRegisterRestaurantBinding;
import com.study.restaurant.view.RegisterRestaurantNavigator;
import com.study.restaurant.viewmodel.RegisterRestaurantViewModel;

public class RegisterRestaurantActivity extends AppCompatActivity implements RegisterRestaurantNavigator {


    RegisterRestaurantViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterRestaurantBinding activityRegisterRestaurantBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_restaurant);
        vm = new RegisterRestaurantViewModel(this);
        activityRegisterRestaurantBinding.setVm(vm);
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


    @Override
    public void goMap() {
        MapsActivity.go(this);
    }

    @Override
    public void onFinish() {
        Toast.makeText(this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
        {
            String address = data.getStringExtra("address");
            vm.setLocation(address);
            vm.setLat(data.getStringExtra("lat"));
            vm.setLng(data.getStringExtra("lng"));
        }
    }
}
