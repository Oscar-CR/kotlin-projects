package org.bedu.sharedreto1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.bedu.sharedreto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        val PREF_NAME = "name"
        val PREF_MAIL = "mail"

    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {


        }
    }

    private fun saveValues(){
        val string = binding.etMail.text.toString()
        val number = binding.etPass.text.toString()
        val checked = bindig.switch1.isChecked

        preferences.edit()
            .putString(STRING, string)
            .putBoolean(BOOLEAN,checked)
            .putFloat(NUMBER,number)
            .apply()
    }
    private fun setValues(){
        val string = preferences.getString(STRIN,"")
        val string = preferences.getBoolean(BOOLEAN, false)
        val number = preferences.getFloat(NUMBER,0f)

        bindig.etString.setText(string)
        bindig.switch1.isChecked = boolean
        bindig.etNumber.setText(number.toString())
    }


    private fun isLogged(){
        if (binding.etMail.length()==0 && binding.etPass.length()==0){
            Toast.makeText(this,"Agrega datos", Toast.LENGTH_SHORT).show()
        }else{
            val i = Intent(this, LoggedActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
    }

}