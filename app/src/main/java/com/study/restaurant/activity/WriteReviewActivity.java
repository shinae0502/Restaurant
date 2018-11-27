package com.study.restaurant.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kakao.util.apicompatibility.APICompatibility;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.dialog.UploadProgressDialog;
import com.study.restaurant.model.MyImage;
import com.study.restaurant.model.StoreKeyword;
import com.study.restaurant.util.CountingFileRequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.zelory.compressor.Compressor;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WriteReviewActivity extends AppCompatActivity {

    ArrayList<MyImage> selectedImageList;
    Button btnConfirm;
    EditText edReview;
    View rating1;
    View rating2;
    View rating3;

    StoreKeyword storeKeyword;
    List<File> compressedImagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        storeKeyword = getIntent().getParcelableExtra("storeKeyword");

        selectedImageList = getIntent().getParcelableArrayListExtra("selectedImageList");
        btnConfirm = findViewById(R.id.btnConfirm);
        edReview = findViewById(R.id.edReview);

        rating1 = findViewById(R.id.rating1);
        rating2 = findViewById(R.id.rating2);
        rating3 = findViewById(R.id.rating3);

        edReview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btnConfirm.setText(String.format("완료(%s/2000)", "" + charSequence.length()));

                if (charSequence.length() > 0)
                    btnConfirm.setBackgroundResource(R.color.orange);
                else
                    btnConfirm.setBackgroundResource(R.color.gray);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //기본 맛있다 선택
        rating1.setSelected(true);

        rating1.setOnClickListener(view -> {
            rating1.setSelected(true);
            rating2.setSelected(false);
            rating3.setSelected(false);
        });
        rating2.setOnClickListener(view -> {
            rating1.setSelected(false);
            rating2.setSelected(true);
            rating3.setSelected(false);
        });
        rating3.setOnClickListener(view -> {
            rating1.setSelected(false);
            rating2.setSelected(false);
            rating3.setSelected(true);
        });

        btnConfirm.setOnClickListener(view -> {
            //업로드 팝업 띄우기
            //선택된 사진
            //평점
            //글자 메시지 띄우기
            /*

             */
            handler.sendEmptyMessage(MSG_START_UPLOAD);
        });

        uploadProgressDialog = new UploadProgressDialog(this);

    }

    public static void go(AppCompatActivity appCompatActivity, ArrayList<MyImage> myImageArrayList, StoreKeyword storeKeyword) {
        Intent intent = new Intent(appCompatActivity, WriteReviewActivity.class);
        intent.putExtra("selectedImageList", myImageArrayList);
        intent.putExtra("storeKeyword", storeKeyword);
        appCompatActivity.startActivity(intent);
    }

    public void clickClose(View v) {
        onBackPressed();
    }

    private final int MSG_START_UPLOAD = 0;
    private final int MSG_IMAGE_COMPRESS = 1;
    private final int MSG_FILE_UPLOAD = 3;
    private final int MSG_FILE_UPLOAD_FINISH = 4;

    private int imageCompressCount = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_START_UPLOAD:
                    uploadProgressDialog.show();
                    uploadProgressDialog.setProgressMessage("start uploading.");
                    sendEmptyMessageDelayed(MSG_IMAGE_COMPRESS, 1000);
                    //압출 될 이미지 경로 초기화 하기.
                    compressedImagePaths = new ArrayList<>();
                    imageCompressCount = 0;


                    break;

                case MSG_IMAGE_COMPRESS:
                    String message = "image compressing..";
                    message = message + "\n" + selectedImageList.get(imageCompressCount).getData();
                    uploadProgressDialog.setProgressMessage(message);
                    uploadProgressDialog.showImage(selectedImageList.get(imageCompressCount).getData());

                    File actualImage = new File(selectedImageList.get(imageCompressCount).getData());
                    imageCompressCount++;

                    new Compressor(WriteReviewActivity.this)
                            .compressToFileAsFlowable(actualImage)
                            .subscribeOn(Schedulers.io())
                            .subscribe(file -> {
                                Log.d("sarang", file.getPath());
                                compressedImagePaths.add(file);

                                sendEmptyMessageDelayed(
                                        imageCompressCount > selectedImageList.size() - 1 ?
                                                MSG_FILE_UPLOAD : MSG_IMAGE_COMPRESS, 500);
                            }, throwable -> throwable.printStackTrace());
                    break;

                case MSG_FILE_UPLOAD:
                    uploadProgressDialog.hideImage();
                    message = "image compress complete..\nFile upload..";
                    uploadProgressDialog.setProgressMessage(message);

                    //멀티파트 업로드 구현하기
                    //1.압축된 이미지 경로 가져오기

                    int score = rating1.isSelected() ? 5 : rating2.isSelected() ? 3 : rating3.isSelected() ? 1 : 0;
                    Map<String, RequestBody> params = new HashMap<>();
                    params.put("user_id", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(WriteReviewActivity.this).loadUser().user_id));
                    params.put("store_id", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getStore_id()));
                    params.put("user_name", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(WriteReviewActivity.this).loadUser().name));
                    params.put("profile_img", RequestBody.create(MediaType.parse("text/plain"), ""));
                    params.put("score", RequestBody.create(MediaType.parse("text/plain"), "" + score));
                    params.put("contents", RequestBody.create(MediaType.parse("text/plain"), "" + edReview.getText().toString()));
                    params.put("tag1", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getRestaurant_name()));
                    params.put("tag2", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getCity_name()));

                    MultipartBody.Part pic1 = null;
                    MultipartBody.Part pic2 = null;
                    MultipartBody.Part pic3 = null;
                    MultipartBody.Part pic4 = null;
                    MultipartBody.Part pic5 = null;
                    MultipartBody.Part pic6 = null;
                    MultipartBody.Part pic7 = null;
                    MultipartBody.Part pic8 = null;
                    MultipartBody.Part pic9 = null;
                    MultipartBody.Part pic0 = null;

                    for (int i = 0; i < compressedImagePaths.size(); i++) {
                        //1. 파일 생성
                        final File file = compressedImagePaths.get(i);

                        //2. 파일 널체크
                        if (file == null) {
                            Log.d("sarang", "file is null");
                            return;
                        }

                        //3. 파일 요청 파라미터 만들기
                        //파일 업로드 프로그레스 처리
                        CountingFileRequestBody requestFile = new CountingFileRequestBody(file, "image/*"
                                , num -> WriteReviewActivity.this.runOnUiThread(() -> {
                            float percentage = ((float) num / (float) file.length()) * 100;
                            uploadProgressDialog.setProcessProgress((int) percentage);
                        }));

                        //4. 멀티파트 파라미터 만들기
                        if (i == 0) {
                            pic1 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 1) {
                            pic2 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 2) {
                            pic3 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 3) {
                            pic4 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 4) {
                            pic5 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 5) {
                            pic6 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 6) {
                            pic7 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 7) {
                            pic8 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 8) {
                            pic9 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        } else if (i == 9) {
                            pic0 = MultipartBody.Part.createFormData("userfile[]", file.getName(), requestFile);
                        }

                    }

                    ApiManager.getInstance().registerNews(params, pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic0,
                            new ApiManager.CallbackListener() {
                                @Override
                                public void callback(String result) {
                                    uploadProgressDialog.setProgressMessage("업로드가 완료되었습니다.");
                                    sendEmptyMessageDelayed(MSG_FILE_UPLOAD_FINISH, 1000);
                                }

                                @Override
                                public void failed(String msg) {

                                }
                            });

                    break;

                case MSG_FILE_UPLOAD_FINISH:
                    uploadProgressDialog.dismiss();
                    ((GlobalApplication)getApplication()).finishActivity();
                    finish();
                    break;
            }
        }
    };


    UploadProgressDialog uploadProgressDialog;
}
