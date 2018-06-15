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
    fun userLoad()
    {
        BananaPreference.getInstance(InstrumentationRegistry.getContext()).loadUser()
    }
}
