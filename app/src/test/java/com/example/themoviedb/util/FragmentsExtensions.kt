package com.example.themoviedb.util

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.testing.R
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.themoviedb.HiltTestActivity

inline fun <reified F : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    crossinline action: Fragment.() -> Unit = {}
) {
    val componentName = ComponentName(
        ApplicationProvider.getApplicationContext(),
        HiltTestActivity::class.java
    )
    val startActivityIntent = Intent.makeMainActivity(componentName)
    ActivityScenario.launch<HiltTestActivity>(startActivityIntent).onActivity { activity ->
        val fragment = activity.supportFragmentManager.fragmentFactory
            .instantiate(requireNotNull(F::class.java.classLoader), F::class.java.name)
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, fragment::class.java.name)
            .commitNow()
        fragment.action()
    }
}

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    fragmentClass: T,
    crossinline action: Fragment.() -> Unit = {}
) {
    val componentName = ComponentName(
        ApplicationProvider.getApplicationContext(),
        HiltTestActivity::class.java
    )
    val startActivityIntent = Intent.makeMainActivity(componentName)
    ActivityScenario.launch<HiltTestActivity>(startActivityIntent).onActivity { activity ->
        val fragment = activity.supportFragmentManager.fragmentFactory
            .instantiate(requireNotNull(fragmentClass::class.java.classLoader), fragmentClass::class.java.name)
        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, fragment::class.java.name)
            .commitNow()
        fragment.action()
    }
}
