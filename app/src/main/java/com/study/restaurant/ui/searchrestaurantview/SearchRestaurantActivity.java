package com.study.restaurant.ui.searchrestaurantview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.ui.GlobalApplication;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreKeyword;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchRestaurantActivity extends AppCompatActivity {

    private RecyclerView storeKeywordRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        //위치 요청하기

        //받은 위치로 주변 식당 검색요청

        //사용자가 키워드롤 입력했을 때 검색 요청하기
        ((EditText) findViewById(R.id.keyword)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    Message msg = new Message();
                    msg.obj = charSequence.toString();
                    msg.what = 0;
                    handler.removeMessages(0);
                    handler.sendMessageDelayed(msg, 300);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        storeKeywordRv = findViewById(R.id.storeKeywordRv);

        storeKeywordRv.setLayoutManager(new LinearLayoutManager(this));

        storeKeywordRv.setAdapter(new StoreKeywordRvAdt());


    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Map<String, String> param = new HashMap<>();
            param.put("keyword", msg.obj.toString());
            ApiManager.getInstance().getStoreKeyword(param, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    Type type = new TypeToken<ArrayList<StoreKeyword>>() {
                    }.getType();
                    ArrayList<StoreKeyword> storeKeywordArrayList = new Gson().fromJson(result, type);
                    ((StoreKeywordRvAdt) storeKeywordRv.getAdapter()).setKeywordList(storeKeywordArrayList);
                }

                @Override
                public void failed(String msg) {

                }
            });
        }
    };

    public static void go(AppCompatActivity activity) {
        Intent intent = new Intent(activity, SearchRestaurantActivity.class);
        activity.startActivity(intent);
    }

    private class StoreKeywordRvAdt extends RecyclerView.Adapter {
        ArrayList<StoreKeyword> storeKeywordArrayList;

        public void setKeywordList(ArrayList<StoreKeyword> storeKeywordArrayList) {
            this.storeKeywordArrayList = storeKeywordArrayList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new StoreKeywordRvHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store_keyword, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((StoreKeywordRvHolder) holder).restaurant_name.setText(storeKeywordArrayList.get(position).getRestaurant_name());
            ((StoreKeywordRvHolder) holder).address.setText(
                    storeKeywordArrayList.get(position).getCity_name() + " " +
                            storeKeywordArrayList.get(position).getRegion_name()
            );

            ((StoreKeywordRvHolder) holder).itemView.setOnClickListener(view -> {
                ((GlobalApplication) getApplication()).addActivity(SearchRestaurantActivity.this);
                Store store = new Store();
                store.setStore_id(storeKeywordArrayList.get(position).getStore_id());
                store.setStoreName(storeKeywordArrayList.get(position).getRestaurant_name());
                store.setStore_name(storeKeywordArrayList.get(position).getRestaurant_name());
                SelectPictureActivity.go(SearchRestaurantActivity.this, BananaConstants.PictureUploadMode.REVIEW, store);
            });
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (storeKeywordArrayList != null)
                count = storeKeywordArrayList.size();
            return count;
        }
    }

    private class StoreKeywordRvHolder extends RecyclerView.ViewHolder {

        TextView restaurant_name;
        TextView address;

        public StoreKeywordRvHolder(View itemView) {
            super(itemView);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            address = itemView.findViewById(R.id.address);
        }
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
