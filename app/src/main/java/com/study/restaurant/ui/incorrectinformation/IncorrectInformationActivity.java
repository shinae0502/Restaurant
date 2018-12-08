package com.study.restaurant.ui.incorrectinformation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.model.Store;
import com.study.restaurant.ui.incorrectinformation.IncorrectInformationFragment;

public class IncorrectInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incorrect_information_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, IncorrectInformationFragment.newInstance())
                    .commitNow();
        }
    }

    public static void go(Context context, Store store) {
        Intent intent = new Intent(context, IncorrectInformationActivity.class);
        intent.putExtra("store", store);
        context.startActivity(intent);

    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
