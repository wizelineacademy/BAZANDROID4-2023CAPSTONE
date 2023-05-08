package com.example.themoviedb.presentation.fragment

import android.os.Build
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentNowPlayingBinding
import com.example.themoviedb.util.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
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
class NowPlayingFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `test now playing fragment displays movies`() {
        launchFragmentInHiltContainer(
            fragmentClass = NowPlayingFragment()
        ) {
            assertThat(this).isInstanceOf(NowPlayingFragment::class.java)

            onView(withId(R.id.rvMovies)).check(ViewAssertions.matches(isDisplayed()))
        }
    }
}
