package com.andresrivas.bazpeliculasyseries.presentation.view.login

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andresrivas.bazpeliculasyseries.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PasswordLoginFragmentTest {
    private lateinit var scenario: FragmentScenario<PasswordLoginFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @After
    fun tearDown() {
        scenario.close()
    }


    @Test
    fun validateLogin() {
        Espresso.onView(withId(R.id.email_edit_text)).perform(typeText("User mock"))
        Espresso.onView(withId(R.id.password_edit_text)).perform(typeText("1234"))
        Espresso.onView(withId(R.id.login_button)).perform(click())
    }
}