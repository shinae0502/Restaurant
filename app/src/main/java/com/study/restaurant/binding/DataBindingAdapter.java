package com.study.restaurant.binding;

import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.adapter.AroundRestaurantRvAdt;
import com.study.restaurant.adapter.ReviewRvAdt;
import com.study.restaurant.adapter.StoreRvAdt;
import com.study.restaurant.adapter.StoryRvAdt;
import com.study.restaurant.adapter.TopListRvAdt;
import com.study.restaurant.model.Review;
import com.study.restaurant.model.Store;
import com.study.restaurant.model.Story;
import com.study.restaurant.model.TopList;
import com.study.restaurant.util.MyGlide;
import com.study.restaurant.viewmodel.FindRestaurantViewModel;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.study.restaurant.util.GlideOptions.bitmapTransform;

public class DataBindingAdapter {
    @BindingAdapter({"app:onScrollListener", "app:vm"})
    public static void bind(RecyclerView recyclerView
            , RecyclerView.OnScrollListener onScrollListener
            , FindRestaurantViewModel vm
    ) {
        ((StoreRvAdt) recyclerView.getAdapter()).setVm(vm);
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @BindingAdapter({"app:adapter"})
    public static void setAdapterBind(RecyclerView recyclerView
            , RecyclerView.Adapter rvAdt) {
        recyclerView.setAdapter(rvAdt);
    }

    @BindingAdapter({"app:items"})
    public static void setItemsBind(RecyclerView recyclerView
            , ArrayList<Review> items) {
        ((ReviewRvAdt) recyclerView.getAdapter()).setReviews(items);
    }

    @BindingAdapter({"app:enabled"})
    public static void setEanbledBind(ViewGroup viewGroup, boolean b) {
        viewGroup.setEnabled(b);
    }

    @BindingAdapter({"app:items"})
    public static void setItemsBind1(RecyclerView recyclerView
            , ArrayList<TopList> items) {
        ((TopListRvAdt) recyclerView.getAdapter()).setTopLists(items);
    }

    @BindingAdapter({"app:items"})
    public static void setItemsBind2(RecyclerView recyclerView
            , ArrayList<Store> items) {
        ((AroundRestaurantRvAdt) recyclerView.getAdapter()).setStoreList(items);
    }

    @BindingAdapter({"app:items"})
    public static void setItemsBind3(RecyclerView recyclerView
            , ArrayList<Story> items) {
        ((StoryRvAdt) recyclerView.getAdapter()).setStoryList(items);
    }

    @BindingAdapter({"app:setSpanSizeLookup"})
    public static void spanSizeLookupBind(RecyclerView recyclerView, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanSizeLookup(spanSizeLookup);
    }

    @BindingAdapter({"app:spanCount"})
    public static void spanCountBind(RecyclerView recyclerView, int count) {
        ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanCount(count);
    }

    @BindingAdapter({"app:selected"})
    public static void selectedBind(ViewGroup viewGroup
            , boolean selected
    ) {
        viewGroup.setSelected(selected);
    }

    @BindingAdapter({"app:onPageChangeListener"})
    public static void pageChangeBind(ViewPager viewPager, ViewPager.OnPageChangeListener onPageChangeListener) {
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @BindingAdapter({"app:offscreenPageLimit"})
    public static void pageLimitBind(ViewPager viewPager, int limit) {
        viewPager.setOffscreenPageLimit(limit);
    }

    @BindingAdapter({"app:glideLoadImage"})
    public static void glideLoadImage(ImageView imageView, String url) {
        MyGlide.with(imageView.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @BindingAdapter({"app:glideBlurImage"})
    public static void glideBlurImage(ImageView imageView, String url) {
        MyGlide.with(imageView.getContext())
                .load(url)
                .apply(bitmapTransform(new BlurTransformation(25, 3)))
                //.transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    @BindingAdapter({"app:textWhatcher"})
    public static void setTextWhatcher(EditText editText, TextWatcher textWatcher) {
        editText.addTextChangedListener(textWatcher);
    }

    @BindingAdapter({"app:setPaintFlags"})
    public static void setPaintFlags(TextView textView, boolean b) {
        if (b) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & Paint.UNDERLINE_TEXT_FLAG);
        }
    }

    @BindingAdapter("app:setPagerAdater")
    public static void setPagerAdapter(ViewPager viewPager, FragmentStatePagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("app:visibility")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
}
