package com.study.restaurant.ui.picturereview;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Review;

/**
 * {@link R.layout#picture_review_fragment}
 */
public class PictureReviewViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public MutableLiveData<Boolean> isExistsHeart_id = new MutableLiveData<>();

    public void clickProfile(View v, String userId) {

    }

    public void clickLike(View v, String news_id) {
        if (v.isSelected()) {
            ApiManager.getInstance().deleteLikeReview(v.getContext(), news_id, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    isExistsHeart_id.setValue(false);
                }

                @Override
                public void failed(String msg) {

                }
            });
        } else {
            ApiManager.getInstance().addLikeReview(v.getContext(), news_id, new ApiManager.CallbackListener() {
                @Override
                public void callback(String result) {
                    isExistsHeart_id.setValue(true);
                }

                @Override
                public void failed(String msg) {

                }
            });
        }

    }

    public void clickShare(Review review) {

    }

    public void clickMore(Review review) {

    }

    public View.OnClickListener getPhotoViewerListener() {
        return view -> {

        };
    }
}
