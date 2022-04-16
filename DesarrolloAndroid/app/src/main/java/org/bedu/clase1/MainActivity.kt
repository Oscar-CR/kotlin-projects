package org.bedu.clase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var btnAccept : Button
    private lateinit var text: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAccept = findViewById(R.id.btnAccept)
        text = findViewById(R.id.btnAccept)

        btnAccept.setOnClickListener{
            text.text=getString(R.string.texto_mostrado)
        }
    }
}