package org.bedu.sharedpreferences

import android.app.Application

class UserLogin: Application() {

    companion object{
        lateinit var pref: Preferences
    }

    override fun onCreate() {
        super.onCreate()
        pref = Preferences(applicationContext)
    }
}