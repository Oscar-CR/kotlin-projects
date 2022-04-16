package org.bedu.dependencias

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    @Named("String1")
    lateinit var testString: String

    @Inject
    @Named("String2")
    lateinit var testString2: String

    @Inject
    @Named("random")
    lateinit var random: String

    @Inject
    @Named("randomMain")
    lateinit var randomMain: String

    private lateinit var btnChange: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnChange=findViewById(R.id.btnChange)

        Log.e("MainActivity", testString)
        Log.e("MainActivity", testString2)


        btnChange.setOnClickListener {
            val intent=Intent(this, OtherActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", random)
        Log.e("MainActivity", randomMain)
    }
}
