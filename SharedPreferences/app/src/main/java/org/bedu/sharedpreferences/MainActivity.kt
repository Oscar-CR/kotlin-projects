package org.bedu.sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import org.bedu.sharedpreferences.UserLogin.Companion.pref

class MainActivity : AppCompatActivity() {

    private lateinit var btnContinue: Button
    private lateinit var etName:EditText
    private lateinit var cbVIP: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContinue = findViewById(R.id.btnContinue)
        etName = findViewById(R.id.etName)
        cbVIP = findViewById(R.id.cbVIP)
        initUI()
        checkUserValues()
    }
    fun checkUserValues(){
        if (pref.getName().isNotEmpty()){
            goToDetail()
        }
    }

    fun initUI(){
        btnContinue.setOnClickListener {
            accessToDetail()
        }
    }

    fun accessToDetail(){
        if (etName.text.toString().isNotEmpty()){
            pref.saveName(etName.text.toString())
            pref.saveVIP(cbVIP.isChecked)
            goToDetail()
        }else{

        }
    }

    fun goToDetail(){
        val intent =Intent(this, ResultActivity::class.java).apply {  }
        startActivity(intent)
    }
}