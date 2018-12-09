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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.databinding.ActivityPhotoDetailBinding;
import com.study.restaurant.fragment.PhotoFragment;
import com.study.restaurant.model.News;
import com.study.restaurant.model.Review;
import com.study.restaurant.ui.profileview.ProfileActivity;
import com.study.restaurant.util.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 식당 상세화면을 통해 진입했을경우 로딩이 계속 되어야함
 */
public class PhotoDetailActivity extends AppCompatActivity {

    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPhotoDetailBinding activityPhotoDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_detail);
        activityPhotoDetailBinding.setReview(getReview().get(0));
        ViewPager photoPager = findViewById(R.id.photoPager);
        PhotoPageAdater photoPageAdater = new PhotoPageAdater(getSupportFragmentManager());
        photoPageAdater.setReview(getReview());
        photoPager.setAdapter(photoPageAdater);

        photoPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Logger.v("" + photoPager.getCurrentItem() + "," + photoPageAdater.getCount() + "," + position);
                if (photoPager.getCurrentItem() == photoPageAdater.getCount() - 1) {
                    if (isLoading) {
                        return;
                    }
                    isLoading = true;
                    Toast.makeText(PhotoDetailActivity.this, "마지막 페이지", Toast.LENGTH_SHORT).show();
                    ApiManager.getInstance().requestReview(new ApiManager.CallbackListener() {
                        @Override
                        public void callback(String result) {
                            isLoading = false;
                            Type type = new TypeToken<ArrayList<News>>() {
                            }.getType();
                            ArrayList<News> newsArrayList = new Gson().fromJson(result, type);
                            photoPageAdater.appendNews(newsArrayList);
                        }

                        @Override
                        public void failed(String msg) {

                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

        public void appendNews(ArrayList<News> newsArrayList) {
            review.addAll(newsArrayList);
            notifyDataSetChanged();
        }
    }

    public void clickClose(View v) {
        onBackPressed();
    }

    public void seeMore(View v) {

    }
}
