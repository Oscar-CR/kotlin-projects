package org.bedu.login.models

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class UserRepository(private val UserDao:UserDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    fun getUsers(): LiveData<List<User>> {
        return UserDao.getUsers()
    }

    //suspend fun populateUsers(users:List<User>)= withContext(ioDispatcher){
    //    return@withContext UserDao.insertAll(users)
    //}

    suspend fun removeUser(user: User){
        coroutineScope {
            launch { UserDao.removeUser(user) }
        }
    }

    suspend fun addUser(user: User){
        coroutineScope {
            launch { UserDao.insertUser(user) }
        }
    }

    fun populateUsers(vehicles: List<User>) {
        return UserDao.insertAll(vehicles)
    }

}