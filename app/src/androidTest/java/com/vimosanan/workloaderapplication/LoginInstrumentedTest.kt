package com.vimosanan.workloaderapplication

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.vimosanan.workloaderapplication.ui.login.LoginActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest{

    private var activity: LoginActivity? = null

    @Before
    fun setUp(){
        activity = rule.activity
    }

    @Rule
    @JvmField
    val rule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun check_user_can_enter_username(){
        Espresso.onView(ViewMatchers.withId(R.id.edtUsername))
            .perform(ViewActions.typeText("+65 2452 2383"))
    }

    @Test
    fun check_user_can_enter_password(){
        Espresso.onView(ViewMatchers.withId(R.id.edtPassword))
            .perform(ViewActions.typeText("alexander"))
    }

    @Test
    fun check_login_work_as_expected(){
        Espresso.onView(ViewMatchers.withId(R.id.edtUsername))
            .perform(ViewActions.typeText("+6281313272005"))

        Espresso.onView(ViewMatchers.withId(R.id.edtPassword))
            .perform(ViewActions.typeText("alexander"), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).perform(ViewActions.click())
            .check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        activity = null
    }
}
