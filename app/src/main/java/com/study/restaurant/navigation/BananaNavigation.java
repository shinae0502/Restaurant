package com.study.restaurant.navigation;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.model.Store;
import com.study.restaurant.ui.pictureuploadview.PictureUploadActivity;
import com.study.restaurant.ui.restaurantdetailview.RestaurantDetailActivity;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;

import java.util.ArrayList;

public class BananaNavigation {
    public interface SplashNavigation {
        /**
         * {@link com.study.restaurant.ui.loginview.LoginActivity}
         * {@link R.layout#activity_login}
         */
        void goLogin();

        /**
         * {@link com.study.restaurant.ui.mainview.MainActivity}
         * {@link R.layout#activity_main}
         */
        void goMain();
    }

    public interface LoginNavigation {
        /**
         * {@link com.study.restaurant.ui.mainview.MainActivity}
         * {@link R.layout#activity_main}
         */
        void goMain();
    }

    public interface MainNavigation {
        /**
         * {@link com.study.restaurant.ui.findrestaurantview.FindRestaurantFragment}
         * {@link R.layout#fragment_find_restaurant}
         */
        void goFindRestaurant();

        /**
         * {@link com.study.restaurant.fragment.MangoPickFragment}
         * {@link R.layout#fragment_mango_pick}
         */
        void goMangoPick();

        void goRegister();

        /**
         * {@link com.study.restaurant.fragment.NewsFragment}
         * {@link R.layout#fragment_news}
         */
        void goNews();

        /**
         * {@link com.study.restaurant.fragment.MyInformationFragment}
         * {@link R.layout#fragment_myinformation}
         */
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
