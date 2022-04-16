package org.bedu.sharedpreferences

import android.content.Context

class Preferences(val context : Context) {
    val SHARED_NAME = "MyName"
    val SHARED_USER_NAME ="Username"
    val SHARED_VIP= "vip"
    val storage = context.getSharedPreferences(SHARED_NAME,0)

    fun saveName(name:String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveVIP(vip: Boolean){
        storage.edit().putBoolean(SHARED_VIP, vip).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_USER_NAME,"")!!
    }

    fun getVIP():Boolean{
        return storage.getBoolean(SHARED_VIP,false)!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }


}