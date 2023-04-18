package com.jecruzv.capstonewl.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.jecruzv.capstonewl.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = supportFragmentManager.findFragmentById(R.id.nav_home_fragment) as NavHostFragment? ?: return
        val navController= host.navController

        //val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        //bottomNavigationView?.setupWithNavController(navController)
    }
}