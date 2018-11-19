# [구현] [Android] 로그인 개발하기(UI)

로그인 UI를 보면 3장의 사진이 오른쪽 왼쪽으로 번갈아 움직이며 fade in, fade out 되는 방식이다.
일단 서버쪽 구현하는게 먼저여서 대략적으로 비슷하게 만들기만했다. 나중에 시간이 되면 아주 정확하게 똑같이 만들
도록 하자.

```java
private class LoginBackgroundChanger extends Handler {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        currentAnim = currentAnim == R.anim.left_to_right ? R.anim.right_to_left : R.anim.left_to_right;

        switch (currentBg) {
            case R.drawable.img_intro_bg_1:
                currentBg = R.drawable.img_intro_bg_2;
                bg1.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, currentAnim));
                break;

            case R.drawable.img_intro_bg_2:
                currentBg = R.drawable.img_intro_bg_3;
                bg2.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, currentAnim));
                break;

            case R.drawable.img_intro_bg_3:
                currentBg = R.drawable.img_intro_bg_1;
                bg3.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this, currentAnim));
                break;
        }
        sendEmptyMessageDelayed(0, 4000);
    }
}
```