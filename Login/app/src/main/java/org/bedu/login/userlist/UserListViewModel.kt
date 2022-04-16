package org.bedu.login.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.login.models.User
import org.bedu.login.models.UserRepository

class UserListViewModel(private val userRepository: UserRepository): ViewModel() {


    val userList = userRepository.getUsers()

    private var _editUserId = MutableLiveData<Int?>()
    val eventEditUser = _editUserId

    fun removeUser(user: User)= viewModelScope.launch {
        userRepository.removeUser(user)
    }

    fun editUser(userId:Int){
        eventEditUser.value =  userId
    }


    private fun prepopulate() {
        val users = listOf(
            User(nombre = "Oscar", correo = "oscar@oscar.com",password = "hola123",telefono = "1234567890")
        )
            userRepository.populateUsers(users)
    }


}