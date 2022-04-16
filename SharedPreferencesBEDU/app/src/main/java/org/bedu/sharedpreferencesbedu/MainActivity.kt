package org.bedu.sharedpreferencesbedu

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.sharedpreferencesbedu.databinding.ActivityMainBinding
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    companion object{
        val PREFS_NAME = "org.bedu.sharedpreferences"
        val STRING = "STRING"
        val NUMBER = "NUMBER"
        val BOOLEAN = "BOOLEAN"
    }

    private lateinit var bindig: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindig = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindig.root)

        preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        setValues()

        bindig.button.setOnClickListener {
            saveValues()
        }
    }
    private fun saveValues(){
        val string = bindig.etString.text.toString()
        val number = bindig.etNumber.text.toString().toFloat()
        val checked = bindig.switch1.isChecked

        preferences.edit()
            .putString(STRING, string)
            .putBoolean(BOOLEAN,checked)
            .putFloat(NUMBER,number)
            .apply()
    }
    private fun setValues(){
        val string = preferences.getString(STRING,"")
        val boolean = preferences.getBoolean(BOOLEAN, false)
        val number = preferences.getFloat(NUMBER,0f)

        bindig.etString.setText(string)
        bindig.switch1.isChecked = boolean
        bindig.etNumber.setText(number.toString())
    }
}