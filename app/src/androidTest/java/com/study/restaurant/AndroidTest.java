package com.study.restaurant;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.study.restaurant.model.StoreSpec;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;

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
}
