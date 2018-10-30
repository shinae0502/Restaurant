package com.study.restaurant.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

import java.security.Permission;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class SelectPictureActivity extends AppCompatActivity {

    RecyclerView selectPicRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);

        selectPicRv = findViewById(R.id.selectPicRv);
        selectPicRv.setLayoutManager(new GridLayoutManager(this, 4));
        selectPicRv.setAdapter(new SelectPicRvAdt());

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            requestLoadAlbum();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void checkPermission() {
        //권한 체크 하기
        int isPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (isPermission == PackageManager.PERMISSION_DENIED) {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage("이미지를 등록하기위해선 저장소 읽기 권한이 필요합니다. 허용하시겠습니까?");
            b.setPositiveButton("yes", (dialogInterface, i) -> SelectPictureActivity.this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0x01));
            b.setNegativeButton("no", (dialogInterface, i) -> finish());
            b.show();
        } else {
            requestLoadAlbum();
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

    ArrayList<Album> mAlbumsList = new ArrayList<>();

    public void requestLoadAlbum() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        ArrayList<String> ids = new ArrayList<String>();
        mAlbumsList.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Album album = new Album();

                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
                album.id = cursor.getString(columnIndex);

                if (!ids.contains(album.id)) {
                    columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
                    album.name = cursor.getString(columnIndex);

                    columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
                    album.coverID = cursor.getLong(columnIndex);

                    mAlbumsList.add(album);
                    ids.add(album.id);
                } else {
                    mAlbumsList.get(ids.indexOf(album.id)).count++;
                }
            }
            cursor.close();
        }
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SelectPictureActivity.class);
        appCompatActivity.startActivity(intent);
    }

    private class SelectPicRvAdt extends RecyclerView.Adapter {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SelectPicHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_select, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    private class SelectPicHolder extends RecyclerView.ViewHolder {

        public SelectPicHolder(View itemView) {
            super(itemView);
        }
    }

    private class Album {
        public String id;
        public String name;
        public long coverID;
        public int count;
    }
}
