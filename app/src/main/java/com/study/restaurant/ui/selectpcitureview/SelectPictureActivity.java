package com.study.restaurant.ui.selectpcitureview;

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
import android.os.Handler;
import android.os.Message;
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
import com.study.restaurant.ui.GlobalApplication;
import com.study.restaurant.ui.pictureuploadview.PictureUploadActivity;
import com.study.restaurant.adapter.SelectPicRvAdt;
import com.study.restaurant.common.BananaBaseActivity;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.databinding.ActivitySelectPictureBinding;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.model.Store;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.ui.writereview.WriteReviewActivity;
import com.study.restaurant.util.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @{link R.layout#activity_select_picture}
 * @{link com.study.restaurant.ui.selectpcitureview.SelectPictureViewModel}
 */
public class SelectPictureActivity extends BananaBaseActivity implements BananaNavigation.SelectPictureNavigation {

    RecyclerView selectPicRv;
    Spinner spFolder;
    Store store;


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
        ((ActivitySelectPictureBinding) getViewDataBinding()).titleBarSelectPicture.setVm((SelectPictureViewModel) getViewModel());
        ((ActivitySelectPictureBinding) getViewDataBinding()).setVm((SelectPictureViewModel) getViewModel());
        ((SelectPictureViewModel) getViewModel()).setSelectPictureNavigation(this);
        spFolder = ((ActivitySelectPictureBinding) getViewDataBinding()).spFolder;
        selectPicRv = ((ActivitySelectPictureBinding) getViewDataBinding()).selectPicRv;
        selectPicRv.setLayoutManager(new GridLayoutManager(this, 4));
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

        if (getPictureUploadMode() == BananaConstants.PictureUploadMode.POST_PICTURE) {
            ((SelectPictureViewModel) getViewModel()).selectedImgList.setValue(getSelectedImgList());
        }
    }

    private ArrayList<MyImage> getSelectedImgList() {
        return getIntent().getParcelableArrayListExtra("selectedImgList");
    }

    @Override
    public void initData() {
        store = getIntent().getParcelableExtra("store");
        getVm().setPictureUploadMode(getPictureUploadMode());
        if (getPictureUploadMode() == BananaConstants.PictureUploadMode.CHECK_IN)
            getVm().getSelectPicRvAdt().setIsCheckIn(true);

    }

    BananaConstants.PictureUploadMode getPictureUploadMode() {
        return (BananaConstants.PictureUploadMode) getIntent().getSerializableExtra("pictureUploadMode");
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

    public Store getStore() {
        return getIntent().getParcelableExtra("store");
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

    public static void go(AppCompatActivity appCompatActivity, BananaConstants.PictureUploadMode pictureUploadMode, Store store) {
        Intent intent = new Intent(appCompatActivity, SelectPictureActivity.class);
        intent.putExtra("store", store);
        intent.putExtra("pictureUploadMode", pictureUploadMode);
        appCompatActivity.startActivityForResult(intent, 0x01);
    }

    public static void go(AppCompatActivity appCompatActivity, BananaConstants.PictureUploadMode pictureUploadMode, Store store, ArrayList<MyImage> selectedImgList) {
        Intent intent = new Intent(appCompatActivity, SelectPictureActivity.class);
        intent.putExtra("store", store);
        intent.putExtra("pictureUploadMode", pictureUploadMode);
        intent.putExtra("selectedImgList", selectedImgList);
        appCompatActivity.startActivityForResult(intent, 0x01);
    }

    @Override
    public void goWriteReview() {
        ((GlobalApplication) getApplication()).addActivity(SelectPictureActivity.this);
        WriteReviewActivity.go(SelectPictureActivity.this,
                ((SelectPictureViewModel) getViewModel()).selectedImgList.getValue(), store);
    }

    @Override
    public void goWriteReviewWithoutPicture() {
        ((GlobalApplication) getApplication()).addActivity(SelectPictureActivity.this);
        WriteReviewActivity.go(SelectPictureActivity.this, new ArrayList<MyImage>(), store);
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

    @Override
    public void goUploadPicture(ArrayList<MyImage> selectedImgList) {
        PictureUploadActivity.go(this, selectedImgList, getStore());
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                finish();
            }
        }.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    public void goUploadPictureOnFinish(ArrayList<MyImage> selectedImgList) {
        Intent intent = new Intent();
        intent.putExtra("selectedImgList", selectedImgList);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
