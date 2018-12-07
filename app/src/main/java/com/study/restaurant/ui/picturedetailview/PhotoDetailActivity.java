package com.study.restaurant.ui.picturedetailview;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivityPhotoDetailBinding;
import com.study.restaurant.fragment.PhotoFragment;
import com.study.restaurant.model.News;
import com.study.restaurant.model.Review;
import com.study.restaurant.ui.profileview.ProfileActivity;

import java.util.ArrayList;

public class PhotoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPhotoDetailBinding activityPhotoDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail);
        activityPhotoDetailBinding.setReview(getReview().get(0));


        ViewPager photoPager = findViewById(R.id.photoPager);
        PhotoPageAdater photoPageAdater = new PhotoPageAdater(getSupportFragmentManager());
        photoPageAdater.setReview(getReview());
        photoPager.setAdapter(photoPageAdater);
    }

    private ArrayList<News> getReview() {
        return getIntent().getParcelableArrayListExtra("review");
    }

    public static void go(Context context, ArrayList<News> review) {
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra("review", review);
        context.startActivity(intent);
    }

    public void clickProfile(View view) {
        ProfileActivity.go(this);
    }

    private class PhotoPageAdater extends FragmentStatePagerAdapter {
        private ArrayList<News> review;

        public PhotoPageAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PhotoFragment photoFragment = new PhotoFragment();
            photoFragment.setImage(review.get(position).getStorePictures().get(0));
            return photoFragment;
        }

        @Override
        public int getCount() {
            int count = 0;
            if (review != null)
                count = review.size();
            return count;
        }

        public void setReview(ArrayList<News> review) {
            this.review = review;
        }
    }

    public void clickClose(View v) {
        onBackPressed();
    }

    public void seeMore(View v) {

    }
}
