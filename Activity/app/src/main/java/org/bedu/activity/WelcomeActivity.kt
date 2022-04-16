package org.bedu.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvHello:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        tvHello = findViewById(R.id.tvHello)

        val bundle = intent.extras

        val name = bundle?.getString(USER_NAME)

        tvHello.text = "Bienvenido $name"
    }
}