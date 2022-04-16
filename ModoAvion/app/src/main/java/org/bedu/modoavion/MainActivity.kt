package org.bedu.modoavion

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val airplaneReceiver = AirplaneReceiver()

    private lateinit var button: Button

    private val fragment= AlertDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button= findViewById(R.id.button)


        // Registramos el receiver de modo avión
        IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }.also { filter -> registerReceiver(airplaneReceiver,filter) }

        button.setOnClickListener {
            fragment.show(supportFragmentManager,"Ctag")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(airplaneReceiver)

    }
}