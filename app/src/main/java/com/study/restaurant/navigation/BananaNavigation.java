package com.study.restaurant.navigation;

import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.model.Store;
import com.study.restaurant.ui.pictureuploadview.PictureUploadActivity;
import com.study.restaurant.ui.restaurantdetailview.RestaurantDetailActivity;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;

import java.util.ArrayList;

public class BananaNavigation {
    public interface SplashNavigation {
        void goLogin();

        void goMain();
    }

    public interface LoginNavigation {
        void goMain();
    }

    public interface MainNavigation {
        void goFindRestaurant();

        void goMangoPick();

        void goRegister();

        void goNews();

        void goMyInformation();

        void registerShowAnimation();

        void hideMenu();

        void rotationMenu(boolean b);
    }

    public interface FindRestaurantNavigation {

        void showSelectRegionPopup();

        void showSortPopup();

        void showBoundaryPopup();

        void showFilterPopup();

        void goSearch();

        void goDeatilRestaurant(Store store);

        void rvToTop();

        void showErrorPopup(String msg);
    }

    public interface CheckInNavigation {
        void goPicture();

        void goCheckInWrite();

        void finish();

        BananaConstants.CheckInPage getCurrentPage();

        void goRegister();
    }

    /**
     * {@link SelectPictureActivity}
     */
    public interface SelectPictureNavigation {
        void goWriteReview();

        void goWriteReviewWithoutPicture();

        void goCheckIn();

        void goUploadPicture(ArrayList<MyImage> selectedImgList);

        void goUploadPictureOnFinish(ArrayList<MyImage> selectedImgList);
    }

    /**
     * Activity {@link RestaurantDetailActivity}
     */
    public interface RestaurantDetailNavigation {
        void goDetailPhoto();

        void goCheckIn();

        void goReview();

        void goMap(Store store);

        void showCallPermissionPopup();
    }

    /**
     * Activity: {@link PictureUploadActivity}
     */
    public interface PictureUploadNavigation {
        void goSelectPicture(ArrayList<MyImage> myImages);

        void finish();
    }
}
