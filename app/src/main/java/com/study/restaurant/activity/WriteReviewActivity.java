package com.study.restaurant.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.model.StoreKeyword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WriteReviewActivity extends AppCompatActivity {

    ArrayList<SelectPictureActivity.MyImage> selectedImageList;
    Button btnConfirm;
    EditText edReview;
    View rating1;
    View rating2;
    View rating3;

    StoreKeyword storeKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        storeKeyword = getIntent().getParcelableExtra("storeKeyword");

        selectedImageList = getIntent().getParcelableArrayListExtra("selectedImageList");
        btnConfirm = findViewById(R.id.btnConfirm);
        edReview = findViewById(R.id.edReview);

        rating1 = findViewById(R.id.rating1);
        rating2 = findViewById(R.id.rating2);
        rating3 = findViewById(R.id.rating3);

        edReview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnConfirm.setText(String.format("완료(%s/2000)", "" + charSequence.length()));

                if (charSequence.length() > 0)
                    btnConfirm.setBackgroundResource(R.color.orange);
                else
                    btnConfirm.setBackgroundResource(R.color.gray);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //기본 맛있다 선택
        rating1.setSelected(true);

        rating1.setOnClickListener(view -> {
            rating1.setSelected(true);
            rating2.setSelected(false);
            rating3.setSelected(false);
        });
        rating2.setOnClickListener(view -> {
            rating1.setSelected(false);
            rating2.setSelected(true);
            rating3.setSelected(false);
        });
        rating3.setOnClickListener(view -> {
            rating1.setSelected(false);
            rating2.setSelected(false);
            rating3.setSelected(true);
        });

        btnConfirm.setOnClickListener(view -> {
            //선택된 사진
            //평점
            //글자 메시지 띄우기
            int score = rating1.isSelected() ? 5 : rating2.isSelected() ? 3 : rating3.isSelected() ? 1 : 0;
            Map<String, RequestBody> params = new HashMap<>();
            params.put("user_id", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().user_id));
            params.put("store_id", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getStore_id()));
            params.put("user_name", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().name));
            params.put("profile_img", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().picture));
            params.put("score", RequestBody.create(MediaType.parse("text/plain"), "" + score));
            params.put("contents", RequestBody.create(MediaType.parse("text/plain"), "" + edReview.getText().toString()));
            params.put("tag1", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getRestaurant_name()));
            params.put("tag2", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getCity_name()));
            ApiManager.getInstance().registerNews(params, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    ((GlobalApplication)getApplication()).finishActivity();
                    finish();
                }

                @Override
                public void failed(String msg) {
                }
            });
        });

    }

    public static void go(AppCompatActivity appCompatActivity, ArrayList<SelectPictureActivity.MyImage> myImageArrayList, StoreKeyword storeKeyword) {
        Intent intent = new Intent(appCompatActivity, WriteReviewActivity.class);
        intent.putExtra("selectedImageList", myImageArrayList);
        intent.putExtra("storeKeyword", storeKeyword);
        appCompatActivity.startActivity(intent);
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
