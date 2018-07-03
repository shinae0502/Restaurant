package com.study.restaurant

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.study.restaurant.activity.LoginActivity
import com.study.restaurant.common.BananaPreference
import com.study.restaurant.presenter.LoginPresenter

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.study.restaurant", appContext.packageName)
    }

    @Test
    fun validateLogin() {
        val loginActivity = LoginPresenter(InstrumentationRegistry.getContext())
        loginActivity.processLogin("[{\"user_id\":\"5\",\"login_platform\":\"kakao\",\"email\":\"sarang628@naver.com\",\"picture\":\"http:\\/\\/k.kakaocdn.net\\/dn\\/baSSVJ\\/btqmthM6cBp\\/ikqEwepJiqwQK4GyV09d3k\\/profile_640x640s.jpg\",\"name\":\"\\uc591\\uc0ac\\ub791\"}]")
    }

    @Test
    fun userLoad() {
        BananaPreference.getInstance(InstrumentationRegistry.getContext()).loadUser()
    }

    @Test
    fun testStoreSpec() {
        var dummy1 = "{\"img1\":\"http://www.globalcardsalud.com/wp-content/uploads/2011/12/banquete.jpg\",\"img2\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVjQ08rDw0pOvd8q7Un6KByZ1GrFMAKGb89JTT1pZQlQVpiSEC\",\"img3\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/220px-Good_Food_Display_-_NCI_Visuals_Online.jpg\",\"img4\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5koo3US24I9QEb-Su1ZUz9nVFW9-10IZ_V7QtDKxMUsARUTIv\",\"img5\":\"https://steptohealth.co.kr/wp-content/uploads/2017/03/foods-to-avoid-eating-for-breakfast-500x283.jpg\",\"updateDate\":\"2018-07-03\",\"openingHours\":\"월-금: 11:10 - 21:30\\n       토: 11:10- 15:30\",\"breaktime\":\"15:00-17:00\",\"prices\":\"만원-2만원 / 1인\",\"menu1\":\"국밥\",\"menu2\":\"정식\",\"menu3\":\"술국\",\"menu1_price\":\"7,000\",\"menu2_price\":\"10,000\",\"menu3_price\":\"13,000\",\"keyword\":\"강남역,에디터,국밥\",\"reviews\":[{\"prifile_pic\":\"a\",\"name\":\"양사랑\",\"review_count\":\"10\",\"follower\":\"234\",\"tag\":\"농민백암왕순대\",\"review\":\"가가가가가\",\"img1\":\"1\",\"img2\":\"2\",\"img3\":\"3\",\"img4\":\"4\",\"img5\":\"5\",\"like\":\"5\",\"comment\":\"10\",\"date\":\"2018-07-03\"},{\"prifile_pic\":\"a\",\"name\":\"자라\",\"review_count\":\"10\",\"follower\":\"234\",\"tag\":\"농민백암왕순대\",\"review\":\"가가가가가\",\"img1\":\"1\",\"img2\":\"2\",\"img3\":\"3\",\"img4\":\"4\",\"img5\":\"5\",\"like\":\"5\",\"comment\":\"10\",\"date\":\"2018-07-03\"},{\"prifile_pic\":\"a\",\"name\":\"고고\",\"review_count\":\"10\",\"follower\":\"234\",\"tag\":\"농민백암왕순대\",\"review\":\"가가가가가\",\"img1\":\"1\",\"img2\":\"2\",\"img3\":\"3\",\"img4\":\"4\",\"img5\":\"5\",\"like\":\"5\",\"comment\":\"10\",\"date\":\"2018-07-03\"}]}"

    }


}
