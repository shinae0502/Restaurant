# 리뷰 쓰기 화면 개발

리뷰 쓰기 화면 UI
<img src = "register_review4.png" height="800px">

이제 업로드만 남았다 이부분은 생각을 좀 많이 해야하는 부분같다. 
이미지를 업로드 했을때 어떤방식으로 저장할 것인지 업로드 절차는 어떻게 할 것인지 리뷰를 
수정해야할 경우, 리뷰를 삭제 할 경우 데이터 처리 등  일단 UI먼저 개발해보도록 하자.

UI개발 화면을 정리해보자

```
1. 상단 타이틀바
2. 맛있다, 괜찮다, 별로 평가하는부분에 대한 selector 처리
3. 텍스트 입력부분
4. 완료버튼
```

아래 링크를 통해 구현한 코드를 확인 할 수 있다.

[activity_write_review.xml](https://github.com/sarang628/Restaurant/blob/master/app/src/main/res/layout/activity_write_review.xml)


기능을 정리해보자.

```
1. 타이틀바의 뒤로가기 기능과 닫기기능
2. 텍스트 작성 시 글자갯수 갱신 기능과 2000자 제한
3. 글자가 없을경우 완료 버튼 비활성화
4. 완료 버튼을 눌렀을 경우 서버로 데이터 전송
	4.1 전송 버튼을 누르면 먼저 이미지 압축 시작하기
    4.2 압축된 이미지를 임시 저장소에 저장하기
    4.3 압축이 끝나면 멀티파트업로드를 이용해 이미지와 입력된 데이터 업로드하기
    4.4 업로드 되는 이미지 프로그래스 처리하기
    4.5 업로드가 끝나면 임시 압축한 파일 지우기
```

1, 2, 3번은 별로 어려울게 없는 기능으로 생략한다.

### 4. 완료 버튼 눌렀을 경우 서버로 데이터 전송

#### 4.1 전송 버튼 누르면 이미지 압축 시작하기. 

이미지 압축을 먼저 시작해보자.

이미지를 압축하기 위한 라이브러리
```
implementation 'id.zelory:compressor:2.1.0'
```

이미지를 압축한뒤 업로드를 위해 압축한 경로를 저장하는 기능
```java
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
            //4.2 압축된 이미지를 임시저장소에 저장하기
            compressedImagePaths.add(file);

            sendEmptyMessageDelayed(
                    imageCompressCount > selectedImageList.size() - 1 ?
                            MSG_FILE_UPLOAD : MSG_IMAGE_COMPRESS, 500);
        }, throwable -> throwable.printStackTrace());
```


#### 4.3 압축이 끝나면 멀티파트업로드를 이용해 이미지와 입력된 데이터 업로드하기
먼저 서버로 전송하는 부분은 POST타입이 아닌 MULTIPART타입으로 전송해야한다.
레트로 핏으로 멀티파트 작상하는 방법

```java
@Multipart
@POST("registerNews.php")
Call<ResponseBody> registerNews(@PartMap() Map<String, RequestBody> params);
```
멀티파트 파라미터 작성 방법
```java
Map<String, RequestBody> params = new HashMap<>();
    params.put("user_id", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().user_id));
    params.put("store_id", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getStore_id()));
    params.put("user_name", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().name));
    params.put("profile_img", RequestBody.create(MediaType.parse("text/plain"), BananaPreference.getInstance(this).loadUser().picture));
    params.put("score", RequestBody.create(MediaType.parse("text/plain"), "" + score));
    params.put("contents", RequestBody.create(MediaType.parse("text/plain"), "" + edReview.getText().toString()));
    params.put("tag1", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getRestaurant_name()));
    params.put("tag2", RequestBody.create(MediaType.parse("text/plain"), storeKeyword.getCity_name()));
```

파일 부분 멀티 파트 작성하기

파일부분은 업로드 되는 상황을 프로그래스 할 수 있는 기능을 추가하였다.

```
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
```

API콜하기

```
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
```

#### 4.5 업로드가 끝나면 임시 압축한 파일 지우기

TODO



리뷰 쓰기가 완료되면 이전에 열려져있던 activity를 다 종료 시켜줘야하는데
쌓여있는데 activity에 대한 관리 방법에 대해 알아보자.

```
찾아낸 방법
1. onActivityResult를 이용해서 각 엑티비티마다 종료해주는 기능을 추가한다.
2. Activity 객채를 static 하게 모아두었다가 finish 시킨다.
```

두 방법다 뭔가 좋은 방법은 아닌것 같지만 다른방법을 찾지 못하여
2번째 방법을 사용하였다.



## 서버 리뷰 구현하기


이제 서버에서 어떻게 처리해야할지 생각해보자.

```
1. 멀티파트 업로드를 통해 데이터와 이미지를 함꼐 받는다.
2. 받은 이미지를 저장하는 방법은 사용자의 아이디에 해당 폴더를 그 안에 업로드 날짜폴더를
또 만든다. 마지막으로 시분초로 파일이름을 만들어 저장한다.
3. 업로드 되는 이미지 프로그래스 처리
4. 데이터 테이블에 삽입
```

```
이미지 활용이 필요한 부분
1. 맛집 상세화면에서 상단부분에 업로드된 이미지 5장 정도를 로드해야한다. 이부분은 리뷰테이블에서 가져와야함.
2. 맛집 화면을 갤러리처럼 보는 부분이 리뷰테이블에서 가져오는 방법
3. 타임라인 부분에 내가 올린 리뷰 사진 리스트가 나온다.
```

위와 같은 이유로 리뷰를 삽입할때 리뷰 테이블와 이미지테이블에 따로따로 데이터를 삽입하기로 결정했다.

리뷰 테이블과 이미지 테이블이다

```
news_id
profile_img
user_name
user_writing
user_follower
hash_tag
score
contents
like_count
reply_count
user_id
store_id
tag1
tag2
date
```

```
pic_id
number
user_id
store_id
url
date
```



서버에서 멀티파트로 데이터를 받았을 때 처리 코드

자세한 설명은 추후 분석할 시간이 온다면..

```
<?php
include 'DBManager.php';
include "model/News.php";

$news = new News();

//파일 저장 패턴
// ../사용자 아이디/업로드 날짜/업로드 시간.확장자
if ($_FILES['userfile']) {
    $file_ary = reArrayFiles($_FILES['userfile']);

    for ($i = 0; $i < count($file_ary); $i++) {
        //파일 저장
        $fileName = saveFile($file_ary[$i]);

        switch ($i) {
            case 0:
                $news->pic1 = sprintf("%s", $fileName);
                break;
            case 1:
                $news->pic2 = sprintf("%s", $fileName);
                break;
            case 2:
                $news->pic3 = sprintf("%s", $fileName);
                break;
            case 3:
                $news->pic4 = sprintf("%s", $fileName);
                break;
            case 4:
                $news->pic5 = sprintf("%s", $fileName);
                break;
            case 5:
                $news->pic6 = sprintf("%s", $fileName);
                break;
            case 6:
                $news->pic7 = sprintf("%s", $fileName);
                break;
            case 7:
                $news->pic8 = sprintf("%s", $fileName);
                break;
            case 8:
                $news->pic9 = sprintf("%s", $fileName);
                break;
            case 9:
                $news->pic0 = sprintf("%s", $fileName);
                break;
        }
    }
    DBManager::registerNews($news);
}

function saveFile($file)
{

    if (!isset($_POST)) {
        echo "아이디 없음";
        return;
    }

    $id = $_POST['user_id'];

    $date = date("Y-m-d");

    $t = microtime(true);
    $micro = sprintf("%06d", ($t - floor($t)) * 1000000);
    $d = new DateTime(date('Y-m-d H:i:s.' . $micro, $t));

    $time = $d->format("Y-m-d H:i:s.u"); // note at point on "u"

    //$time = date("H:i:s.u");

    $createPath = sprintf('/home/yangsarang/Banana/image_upload/%s/%s/', $id, $date);
    if (!file_exists($createPath)) {
        if(mkdir($createPath, 0777, true) == false)
        {
            return "폴더 만들기 실패";
        }
    }

    $target_dir = $createPath;

    $id = $_POST['user_id'];
    $target_file = $createPath . basename($file["name"]);
    $uploadOk = 1;
    $imageFileType = strtolower(pathinfo($target_file, PATHINFO_EXTENSION));
    // Check if image file is a actual image or fake image

//    echo '<br>';
//    echo $file["tmp_name"];
//    echo '<br>';

    if (isset($_POST["submit"])) {
        $check = getimagesize($file["tmp_name"]);
        if ($check !== false) {
            echo "File is an image - " . $check["mime"] . ".";
            $uploadOk = 1;
        } else {
            echo "File is not an image.";
            $uploadOk = 0;
        }
    }
    // Check if file already exists
    if (file_exists($target_file)) {
        echo "Sorry, file already exists.";
        $uploadOk = 0;
    }
    // Check file size
    if ($file["size"] > 50000000) {
        echo "Sorry, your file is too large.";
        $uploadOk = 0;
    }
    // Allow certain file formats
    if ($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
        && $imageFileType != "gif") {
        echo "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
        $uploadOk = 0;
    }
    // Check if $uploadOk is set to 0 by an error
    if ($uploadOk == 0) {
        echo "Sorry, your file was not uploaded.";
        // if everything is ok, try to upload file
    } else {
        $target_file = $target_dir . basename($time) . '.' . $imageFileType;
        if (move_uploaded_file($file["tmp_name"], $target_file)) {
            //echo "The file " . basename($time) . " has been uploaded.";
        } else {
            echo "Sorry, there was an error uploading your file.";
        }

        //echo sprintf('%s/%s/',$id ,$date) . basename($time) . '.' . $imageFileType;

        return sprintf('%s/%s/', $id, $date) . basename($time) . '.' . $imageFileType;
    }
}

function reArrayFiles(&$file_post)
{

    $file_ary = array();
    $file_count = count($file_post['name']);
    //echo $file_count;
    $file_keys = array_keys($file_post);

    for ($i = 0; $i < $file_count; $i++) {
        foreach ($file_keys as $key) {
            $file_ary[$i][$key] = $file_post[$key][$i];
        }
    }

    return $file_ary;
}
```