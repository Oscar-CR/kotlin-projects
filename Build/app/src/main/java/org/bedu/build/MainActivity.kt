package org.bedu.build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var userLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var btn_login :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userLogin=findViewById(R.id.userLogin)
        passwordLogin=findViewById(R.id.passwordLogin)
        btn_login=findViewById(R.id.btn_login)


        btn_login.setOnClickListener {
            Toast.makeText(this,R.string.welcome,Toast.LENGTH_SHORT).show()

        }



    }
}