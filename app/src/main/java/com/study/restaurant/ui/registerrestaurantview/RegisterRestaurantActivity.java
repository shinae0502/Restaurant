package com.study.restaurant.ui.registerrestaurantview;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.ui.mainview.MapsActivity;
import com.study.restaurant.databinding.ActivityRegisterRestaurantBinding;
import com.study.restaurant.view.RegisterRestaurantNavigator;

public class RegisterRestaurantActivity extends AppCompatActivity implements RegisterRestaurantNavigator {


    RegisterRestaurantViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1.데이터 바인딩 초기화
        ActivityRegisterRestaurantBinding activityRegisterRestaurantBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_register_restaurant);
        // 2.뷰모델 초기화
        vm = new RegisterRestaurantViewModel(this);
        // 3.바인더에 뷰모델 등록
        activityRegisterRestaurantBinding.setVm(vm);
    }

    /**
     * 레스토랑 등록화면 이동
     *
     * @param appCompatActivity
     */
    public static void go(AppCompatActivity appCompatActivity) {
        appCompatActivity.startActivity(new Intent(appCompatActivity, RegisterRestaurantActivity.class));
    }

    /**
     * 화면의 백버튼 클릭 시
     *
     * @param v
     */
    public void clickBackBtn(View v) {
        onBackPressed();
    }


    /**
     * 맵버튼 클릭 시 지도 선택화면으로 이동
     */
    @Override
    public void goMap() {
        MapsActivity.go(this);
    }

    @Override
    public void onFinish() {
        Toast.makeText(this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 맵화면에서 돌아왔을때 결과 처리
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String address = data.getStringExtra("address");
            vm.setLocation(address);
            vm.setLat(data.getStringExtra("lat"));
            vm.setLng(data.getStringExtra("lng"));
        }
    }
}
