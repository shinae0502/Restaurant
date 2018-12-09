package com.study.restaurant.test;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreSpec;
import com.study.restaurant.util.Logger;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class Dummy {
    private static Dummy dummy;
    private Application application;
    private Context context;

    public static Dummy getInstance() {
        if (dummy == null)
            dummy = new Dummy();
        return dummy;
    }

    public void setApplication(Application application) {
        this.application = application;
        setContext(application.getBaseContext());
    }

    /**
     * 맛집 상세정보 더미
     *
     * @return
     */
    public String getRestaurantDetail() {
        try {
            Resources res = application.getBaseContext().getResources();
            InputStream in_s = res.openRawResource(R.raw.store_spec_dummy);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getStoreList() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.restaurant_list_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getAddFavorite() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.add_favorite_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getDeleteFavorite() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.delete_favorite_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String checkIn() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.delete_favorite_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String uploadPicture() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.checkin_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getAddLike() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.add_like_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getDeleteLike() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.delete_like_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getNews() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.news_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }

    public String getReview() {
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(R.raw.review_dummy);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Logger.d(e.toString());
        }
        return null;
    }
}
