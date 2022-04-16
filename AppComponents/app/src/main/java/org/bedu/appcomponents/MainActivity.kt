package org.bedu.appcomponents

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttom: Button
    private val receiverTwo= ReceiverTwo()
    private val airplaneReceiver = AirPlaneReceiver()

    companion object{
        const val NAME="NAME"
        const val EMAIL ="EMAIL"
        const val ACTION_NAME = "org.bedu.SALUDO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttom = findViewById(R.id.button)

        buttom.setOnClickListener {
            emit()
        }

        registerAirPlane()

    }

    private fun registerAirPlane() {
        IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }.also { filter -> registerReceiver(airplaneReceiver,filter) }
    }

    override fun onStart(){
        super.onStart()

        IntentFilter().apply {
            addAction(ACTION_NAME)
        }.also { filter -> registerReceiver(receiverTwo,filter) }

    }

    override fun onStop() {
        super.onStop()

        unregisterReceiver(receiverTwo)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneReceiver)
    }

    private  fun emit(){
        // Esta es nuestra información extra a enviar
        val bundle = Bundle().apply {
            putString("NAME","Oscar")
            putString("EMAIL","oscar@bedu.org")
        }

        // Creamos un Intent con nuestro bundle como extra y detonamos el evento con sendBroadcast()

        /*
        Intent(this,ReceiverOne::class.java).apply {
            putExtras(bundle)
        }.let(::sendBroadcast)

         */

        Intent(ACTION_NAME).apply {
            putExtras(bundle)
        }.let(::sendBroadcast)

    }
}