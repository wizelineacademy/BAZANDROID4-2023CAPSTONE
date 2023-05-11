package com.andresrivas.bazpeliculasyseries.presentation.view.login

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.andresrivas.bazpeliculasyseries.R
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    private lateinit var scenario: ActivityScenario<LoginActivity>

    @Before
    fun setUp() {
        val intent = Intent(
            InstrumentationRegistry.getInstrumentation().targetContext,
            LoginActivity::class.java
        )
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun validateLoginAction() {
        Espresso.onView(ViewMatchers.withId(R.id.email_edit_text)).perform(ViewActions.typeText("User mock"))
        Espresso.onView(ViewMatchers.withId(R.id.password_edit_text)).perform(ViewActions.typeText("1234"))
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click())
    }
}