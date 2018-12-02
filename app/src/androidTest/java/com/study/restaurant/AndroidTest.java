package com.study.restaurant;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Region;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.StoreDetail;
import com.study.restaurant.model.StoreSpec;
import com.study.restaurant.model.Story;
import com.study.restaurant.test.Dummy;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AndroidTest {

    @Test
    public void test() {
        try {
            Context appContext = InstrumentationRegistry.getTargetContext();
            Resources res = appContext.getResources();
            InputStream in_s = res.openRawResource(R.raw.store_spec_dummy);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            String s = new String(b);
            //System.out.println(s);

            StoreSpec storeSpec = new Gson().fromJson(s, StoreSpec.class);

            System.out.println(storeSpec.getImg1().getImg1());


        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.toString());
        }
        assertEquals("!!", "!!");
    }

    @Test
    public void retrofitTest() {
        ApiManager.getInstance().getStory(new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                System.out.println(result);
                try {
                    Type listType = new TypeToken<ArrayList<Story>>() {
                    }.getType();
                    ArrayList<Story> story = new Gson().fromJson(result, listType);
                    System.out.println(story.get(0).toString());
                } catch (Exception e) {
                }
            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    @Test
    public void restaurantListDummyTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Dummy.getInstance().setContext(appContext);
    }

    @Test
    public void restaurantDetailDummyTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Dummy.getInstance().setContext(appContext);
        StoreDetail storeDetail = new Gson().fromJson(Dummy.getInstance().getRestaurantDetail(), StoreDetail.class);
        String s =  storeDetail.getOpen_hours().getOffDay();
        storeDetail.getRestaurant();
    }

    @Test
    public void addFavoriteDummyTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Dummy.getInstance().setContext(appContext);
        Store store = new Gson().fromJson(Dummy.getInstance().getAddFavorite(), Store.class);
        assertEquals("1",store.getFavority_id());
    }
}
