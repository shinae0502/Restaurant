package com.study.restaurant.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable.OnPropertyChangedCallback;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.databinding.Observable;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.study.restaurant.BR;
import com.study.restaurant.R;
import com.study.restaurant.model.StoreKeyword;
import com.study.restaurant.util.MyGlide;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SelectPictureActivity extends AppCompatActivity {

    RecyclerView selectPicRv;
    Spinner spFolder;
    StoreKeyword storeKeyword;

    class SelPicVM extends BaseObservable {
        String count;


        @Bindable
        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
            notifyPropertyChanged(BR.count);
        }
    }

    SelPicVM selPicVM = new SelPicVM();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);
        spFolder = findViewById(R.id.spFolder);

        storeKeyword = getIntent().getParcelableExtra("storeKeyword");

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

        Button count = findViewById(R.id.count);
        Button pass = findViewById(R.id.pass);


        count.setText(String.format("완료(%s/20)", selectedImgList.size()));

        selPicVM.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                count.setText(String.format("완료(%s/20)", selPicVM.getCount()));
            }
        });


        count.setOnClickListener(view -> {
            ((GlobalApplication) getApplication()).addActivity(SelectPictureActivity.this);
            WriteReviewActivity.go(SelectPictureActivity.this, selectedImgList, storeKeyword);
        });

        pass.setOnClickListener(view -> {
            ((GlobalApplication) getApplication()).addActivity(SelectPictureActivity.this);
            WriteReviewActivity.go(SelectPictureActivity.this, new ArrayList<MyImage>(), storeKeyword);
        });

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
        appCompatActivity.startActivity(intent);
    }

    ArrayList<MyImage> selectedImgList = new ArrayList<>();

    private class SelectPicRvAdt extends RecyclerView.Adapter {
        private ArrayList<MyImage> myImageList;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SelectPicHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_select, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            SelectPicHolder selectPicHolder = (SelectPicHolder) holder;
            MyGlide.with(holder.itemView.getContext())
                    .load(myImageList.get(position).data)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(selectPicHolder.img);

            if (selectedImgList.contains(myImageList.get(position))) {
                selectPicHolder.dim.setVisibility(View.VISIBLE);
                selectPicHolder.tag.setVisibility(View.VISIBLE);
                selectPicHolder.tag.setText("" + selectedImgList.indexOf(myImageList.get(position)));
            } else {
                selectPicHolder.dim.setVisibility(View.GONE);
                selectPicHolder.tag.setVisibility(View.GONE);
                selectPicHolder.tag.setText("");
            }

            ((SelectPicHolder) holder).img.setOnClickListener((View view) -> {
                boolean isContained = selectedImgList.contains(myImageList.get(position));
                // 이미지를 선택했을때 선택 여부에 따라 딤과 카운트 보여지는 여부
                selectPicHolder.dim.setVisibility(isContained ? View.GONE : View.VISIBLE);
                selectPicHolder.tag.setVisibility(isContained ? View.GONE : View.VISIBLE);

                if (isContained) {
                    selectedImgList.remove(myImageList.get(position));
                    notifyDataSetChanged();
                } else {
                    selectedImgList.add(myImageList.get(position));
                    selectPicHolder.tag.setText("" + (selectedImgList.size() - 1));
                }
                selPicVM.setCount("" + selectedImgList.size());
            });
        }

        @Override
        public int getItemCount() {
            int count = 0;
            if (myImageList != null)
                count = myImageList.size();
            return count;
        }

        public void setMyImageList(ArrayList<MyImage> myImageList) {
            this.myImageList = myImageList;
            notifyDataSetChanged();
        }
    }

    private class SelectPicHolder extends RecyclerView.ViewHolder {

        ImageView img;
        ImageView dim;
        TextView tag;

        public SelectPicHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            dim = itemView.findViewById(R.id.dim);
            tag = itemView.findViewById(R.id.imgtag);
        }
    }

    public static class MyImage implements Parcelable {

        private String data;

        public MyImage() {

        }

        protected MyImage(Parcel in) {
            data = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(data);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<MyImage> CREATOR = new Creator<MyImage>() {
            @Override
            public MyImage createFromParcel(Parcel in) {
                return new MyImage(in);
            }

            @Override
            public MyImage[] newArray(int size) {
                return new MyImage[size];
            }
        };

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object obj) {
            boolean compare = false;

            if (obj != null && obj instanceof MyImage) {
                compare = this.data.equals(((MyImage) obj).data);
            }

            return compare;
        }
    }

    public void clickClose(View v) {
        onBackPressed();
    }
}
