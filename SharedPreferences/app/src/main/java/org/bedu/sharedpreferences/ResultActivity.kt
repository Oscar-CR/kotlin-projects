package org.bedu.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.bedu.sharedpreferences.UserLogin.Companion.pref

class ResultActivity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var tvNamelog: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        btnBack=findViewById(R.id.btnBack)
        tvNamelog=findViewById(R.id.tvNameLog)

        initUI()
    }

    fun initUI(){
        val userName = pref.getName()
        btnBack.setOnClickListener {
            pref.wipe()
            onBackPressed()
        }

        tvNamelog.text = "Bienvenido $userName"

        if(pref.getVIP()){
            setVIPColorBackground()
        }
    }

    private fun setVIPColorBackground() {
       Toast.makeText(this,"Eres VIP", Toast.LENGTH_LONG).show()
    }
}