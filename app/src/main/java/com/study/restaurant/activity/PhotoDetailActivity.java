package com.study.restaurant.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.restaurant.R;
import com.study.restaurant.fragment.PhotoFragment;

public class PhotoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        ViewPager photoPager = findViewById(R.id.photoPager);
        photoPager.setAdapter(new PhotoPageAdater(getSupportFragmentManager()));
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, PhotoDetailActivity.class);
        appCompatActivity.startActivity(intent);
    }

    private class PhotoPageAdater extends FragmentStatePagerAdapter {
        public PhotoPageAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new PhotoFragment();
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
