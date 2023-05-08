package com.example.themoviedb

import android.os.Build
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {
    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
    }

    @Test
    fun `test fragment now playing is launched`() {
        val bottomNavigationView = activity.findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNavigationView.selectedItemId = R.id.nowPlayingFragment

        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val currentDestination = navHostFragment.navController.currentDestination
        assertEquals(R.id.nowPlayingFragment, currentDestination?.id)
    }

    @Test
    fun `test fragment latest is launched`() {
        val bottomNavigationView = activity.findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        bottomNavigationView.selectedItemId = R.id.latestFragment

        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val currentDestination = navHostFragment.navController.currentDestination
        assertEquals(R.id.latestFragment, currentDestination?.id)
    }

    @Test
    fun `test fragment top rated is launched`() {
        val bottomNavigationView = activity.findViewById<BottomNavigationView>(R.id.bottom_nav_view)


        bottomNavigationView.selectedItemId = R.id.topRatedFragment

        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val currentDestination = navHostFragment.navController.currentDestination
        assertEquals(R.id.topRatedFragment, currentDestination?.id)
    }

    @After
    fun tearDown() {
        activity.finish()
    }
}
