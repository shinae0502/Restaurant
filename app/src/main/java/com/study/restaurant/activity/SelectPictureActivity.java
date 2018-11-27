package com.study.restaurant.activity;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.adapter.SelectPicRvAdt;
import com.study.restaurant.common.BananaBaseActivity;
import com.study.restaurant.databinding.ActivitySelectPictureBinding;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.model.StoreKeyword;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.util.Logger;
import com.study.restaurant.viewmodel.SelectPictureViewModel;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SelectPictureActivity extends BananaBaseActivity implements BananaNavigation.SelectPictureNavigation {

    RecyclerView selectPicRv;
    Spinner spFolder;
    StoreKeyword storeKeyword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ViewDataBinding initDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_select_picture);
    }

    @Override
    public ViewModel initViewModel() {
        return ViewModelProviders.of(this).get(SelectPictureViewModel.class);
    }

    @Override
    public void initUI() {
        ((ActivitySelectPictureBinding) getViewDataBinding()).setVm((SelectPictureViewModel) getViewModel());
        ((SelectPictureViewModel) getViewModel()).setSelectPictureNavigation(this);
        spFolder = ((ActivitySelectPictureBinding) getViewDataBinding()).spFolder;
        selectPicRv = ((ActivitySelectPictureBinding) getViewDataBinding()).selectPicRv;
        selectPicRv.setLayoutManager(new GridLayoutManager(this, 4));
        SelectPicRvAdt selectPicRvAdt = new SelectPicRvAdt();
        selectPicRvAdt.setSelectedImageList(((SelectPictureViewModel) getViewModel()).selectedImgList);
        selectPicRv.setAdapter(selectPicRvAdt);
        selectPicRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 5;
                outRect.right = 5;
                outRect.left = 5;
                outRect.top = 5;
            }
        });

        // Creating adapter for spinner
        ArrayList<String> folderList = requestPicFolderList();
        if (folderList == null) {
            return;
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, folderList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spFolder.setAdapter(dataAdapter);

        spFolder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((SelectPicRvAdt) selectPicRv.getAdapter()).setMyImageList(getPicList(folderList.get(i)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ((SelectPictureViewModel) getViewModel()).selectedImgList.observe(this
                , myImages -> {
                    Logger.v("" + myImages.size());
                    ((SelectPictureViewModel) getViewModel()).setCount(myImages.size());
                });
    }

    @Override
    public void initData() {
        storeKeyword = getIntent().getParcelableExtra("storeKeyword");
        if (storeKeyword == null) {
            ((SelectPictureViewModel) getViewModel()).isCheckIn.setValue(true);
            ((SelectPicRvAdt) selectPicRv.getAdapter()).setIsCheckIn(true);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    boolean checkPermission() {
        //권한 체크 하기
        int isPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (isPermission == PackageManager.PERMISSION_DENIED) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage("이미지를 등록하기위해선 저장소 읽기 권한이 필요합니다. 허용하시겠습니까?");
            b.setPositiveButton("yes", (dialogInterface, i) -> SelectPictureActivity.this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0x01));
            b.setNegativeButton("no", (dialogInterface, i) -> finish());
            b.show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x01) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermission();
            }
        }
    }

    public ArrayList<String> requestPicFolderList() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission()) {
            return null;
        }

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        Map<String, String> folderMap = new TreeMap<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                folderMap.put(cursor.getString(columnIndex), cursor.getString(columnIndex));
            }
            cursor.close();
        }

        ArrayList<String> folderList = new ArrayList<>(folderMap.values());
        folderList.add(0, "전체");

        return folderList;
    }

    public ArrayList<MyImage> getPicList(String forderName) {
        Cursor cursor = null;
        Uri uri = null;
        String[] projection = new String[1];
        String selection = null;
        String[] selectionArgs = new String[1];
        String sortOrder = null;
        ArrayList<MyImage> myImageArrayList = new ArrayList<>();
        try {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            projection[0] = MediaStore.MediaColumns.DATA;
            selection = MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME + " LIKE ?";
            selectionArgs[0] = "%" + forderName + "%";
            cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                    MyImage myImage = new MyImage();
                    myImage.setData(cursor.getString(columnIndex));
                    myImageArrayList.add(myImage);
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.e("sr", e.toString());
        }
        if (cursor != null)
            cursor.close();

        return myImageArrayList;
    }

    public static void go(AppCompatActivity appCompatActivity, StoreKeyword storeKeyword) {
        Intent intent = new Intent(appCompatActivity, SelectPictureActivity.class);
        intent.putExtra("storeKeyword", storeKeyword);
        appCompatActivity.startActivityForResult(intent, 0x01);
    }

    @Override
    public void goWriteReview() {
        ((GlobalApplication) getApplication()).addActivity(SelectPictureActivity.this);
        WriteReviewActivity.go(SelectPictureActivity.this,
                ((SelectPictureViewModel) getViewModel()).selectedImgList.getValue(), storeKeyword);
    }

    @Override
    public void goWriteReviewWithoutPicture() {
        ((GlobalApplication) getApplication()).addActivity(SelectPictureActivity.this);
        WriteReviewActivity.go(SelectPictureActivity.this, new ArrayList<MyImage>(), storeKeyword);
    }

    public SelectPictureViewModel getVm() {
        return (SelectPictureViewModel) getViewModel();
    }

    @Override
    public void goCheckIn() {

        if (getVm().selectedImgList.getValue().size() < 1) {
            Toast.makeText(this, "이미지를 선택해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("MyImage", getVm().selectedImgList.getValue().get(0));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
