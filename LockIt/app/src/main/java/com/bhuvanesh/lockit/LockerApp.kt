package com.bhuvanesh.lockit

import android.app.Application
import com.bhuvanesh.appbase.AppPreferences

class LockerApp: Application() {

    companion object {
        private lateinit var mInstance: Application
        fun getAppContext() = mInstance
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this
        AppPreferences.getInstance(this)
    }


}