package com.ryvk.androidfastnetworkingpractical

import android.app.Application
import com.androidnetworking.AndroidNetworking
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidFastNetworkingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}