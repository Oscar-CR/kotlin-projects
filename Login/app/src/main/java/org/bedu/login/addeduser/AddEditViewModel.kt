package org.bedu.login.addeduser

import android.widget.CompoundButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.bedu.login.models.User
import org.bedu.login.models.UserRepository


class AddEditViewModel(private val userRepository: UserRepository): ViewModel() {


    private var _userDone = MutableLiveData<Boolean>(false)
    val userDone = _userDone


    var nombre: String? = null
    var correo: String? = null
    var password: String? = null
    var telefono: String?=null

    fun setName(s: CharSequence, start:Int, before: Int, count:Int){
        nombre = s.toString()
    }

    fun setMail(s: CharSequence, start:Int, before: Int, count:Int){
        correo = s.toString()
    }

    fun setPassword(s: CharSequence, start:Int, before: Int, count:Int){
        password = s.toString()
    }

    fun setPhone(s: CharSequence, start:Int, before: Int, count:Int){
        telefono = s.toString()
    }

    fun newUser() = viewModelScope.launch{
        if ( !nombre.isNullOrBlank() && !correo.isNullOrBlank() && !password.isNullOrBlank() && !telefono.isNullOrBlank()){
            val user = User(
                nombre = nombre!!,
                correo = correo!!,
                password = password!!,
                telefono = telefono!!
            )

            userRepository.addUser(user)

            _userDone.value = true
        }
    }



}

