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

    public StoreSpec getRestaurantDetail() {
        try {
            Resources res = application.getBaseContext().getResources();
            InputStream in_s = res.openRawResource(R.raw.store_spec_dummy);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new Gson().fromJson(new String(b), StoreSpec.class);
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

    public void setContext(Context context) {
        this.context = context;
    }
}
