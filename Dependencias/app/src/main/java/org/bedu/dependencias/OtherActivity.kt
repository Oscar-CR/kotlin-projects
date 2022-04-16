package org.bedu.dependencias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class OtherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}