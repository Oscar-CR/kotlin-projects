package org.bedu.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

const val USER_NAME="org.bedu.activity.WelcomeActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var etName:EditText
    private lateinit var btAccept: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName= findViewById(R.id.etName)
        btAccept= findViewById(R.id.button)

        btAccept.setOnClickListener{
            val bundle=Bundle()
            bundle.putString(USER_NAME, etName.text.toString())

            val intent = Intent(this,WelcomeActivity::class.java).apply{
                putExtras(bundle)
            }

            startActivity(intent)

        }
    }


}