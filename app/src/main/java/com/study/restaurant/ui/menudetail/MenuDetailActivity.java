package com.study.restaurant.ui.menudetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.model.StoreMenu;
import com.study.restaurant.ui.menudetail.MenuDetailFragment;

import java.util.ArrayList;

public class MenuDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MenuDetailFragment.newInstance())
                    .commitNow();
        }
    }

    public static void go(Context context, ArrayList<StoreMenu> storeMenu, String storeName) {
        Intent intent = new Intent(context, MenuDetailActivity.class);
        intent.putExtra("storeMenu", storeMenu);
        intent.putExtra("storeName", storeName);
        context.startActivity(intent);
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
