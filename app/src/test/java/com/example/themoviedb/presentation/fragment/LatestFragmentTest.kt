package com.example.themoviedb.presentation.fragment

import android.os.Build
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.themoviedb.R
import com.example.themoviedb.util.launchFragmentInHiltContainer
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@Config(
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.O_MR1],
    application = HiltTestApplication::class
)
class LatestFragmentTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `test latest fragment displays movies`() {
        launchFragmentInHiltContainer(
            fragmentClass = LatestFragment()
        ) {
            Truth.assertThat(this).isInstanceOf(LatestFragment::class.java)

            Espresso.onView(ViewMatchers.withId(R.id.rvLatest))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}