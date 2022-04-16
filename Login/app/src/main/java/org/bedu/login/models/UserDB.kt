package org.bedu.login.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 2)
abstract class UserDB:RoomDatabase(){

    companion object{
        private var userInstance : UserDB?=null

        const val DB_NAME = "Segurapp_db"

        fun getInstance(context: Context): UserDB?{
            if(userInstance==null){
                synchronized(UserDB::class){
                    userInstance= Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java,
                        DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return  userInstance
        }
    }

    abstract fun UserDao(): UserDao

}