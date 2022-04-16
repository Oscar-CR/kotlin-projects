package org.bedu.login

import android.app.Application
import org.bedu.login.models.UserDB
import org.bedu.login.models.UserRepository

class UserApplication: Application() {

    val userRepository: UserRepository
    get() = UserRepository(
        UserDB.getInstance(this,)!!.UserDao()
    )
}