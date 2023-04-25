package com.jecruzv.capstonewl.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.util.Annotations

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        @Annotations("Entregable 1")
        val host = supportFragmentManager.findFragmentById(R.id.nav_home_fragment) as NavHostFragment? ?: return
    }
}