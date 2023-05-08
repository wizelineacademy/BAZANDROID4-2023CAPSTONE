package com.jecruzv.capstonewl

import android.app.Application
import com.jecruzv.local.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TheMoviesDBApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        applicationContext.deleteDatabase(Constants.DB_NAME)
    }



}