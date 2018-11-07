# [구현] Splash화면 개발하기 (프로그래스바 이미지 만들기)
![스플레시 화면](./splash.gif)

<p>
이 화면에서 중요한 가운데 프로그래스바 처리 부분이다.

ProgressImageView 라는 클래스를 만들어 ImageView를 상속받게하였다.

회색 이미지와 주황색 이미지가 번갈아 가면서 나오며, 한 반정도 돌다가 사라지는식으로 동작을 한다.
progressList에 해당 이미지를 찾아 넣었으며, 애니메이션에 리스너를 달아 애니메이션이 끝
나면 이미지를 변경시켜주는 방식으로 만들었다. 더 좋은 방법은 추후에 여유있을 떄 수정
하는걸로 하겠다.

```java
public class ProgressImageView extends ImageView {

    int count = 0;
    boolean isStop = false;

    int progressList[] = {
            R.drawable.ic_ptr_orange_1,
            R.drawable.ic_ptr_gray_1,
            R.drawable.ic_ptr_orange_2,
            R.drawable.ic_ptr_gray_2,
            R.drawable.ic_ptr_orange_3,
            R.drawable.ic_ptr_gray_3,
            R.drawable.ic_ptr_orange_4,
            R.drawable.ic_ptr_gray_4
    };

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setImageResource(progressList[count]);
        startAnimation();
    }

    private void startAnimation() {

        if (isStop) {
            return;
        }

        count++;
        count %= progressList.length;
        setImageResource(progressList[count]);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(animation);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            isStop = false;
            startAnimation();
        } else if (visibility == GONE) {
            isStop = true;
        }
    }
}
```