package org.bedu.mapbox3

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat

class InfoActivity : AppCompatActivity() {

    private lateinit var accept: Button
    private lateinit var cancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        accept = findViewById(R.id.accept)
        cancel = findViewById(R.id.cancel)

        accept.setOnClickListener {
            getLocation()

        }

        cancel.setOnClickListener {
            finish()
        }
    }

    //Si tenemos permisos y la ubicación está habilitada, obtener la última localización
    @SuppressLint("MissingPermission") // no detecta bien que estamos requiriendo permisos
    private fun getLocation() {
        if (checkPermissions()) { //verificamos si tenemos permisos
            if (isLocationEnabled()) { //localizamos sólo si el GPS está encendido
                Toast.makeText(this,"Permisos activos", Toast.LENGTH_LONG).show()
            }
        } else{
            //si no se tiene permiso, pedirlo
            requestPermissions()
        }
    }

    private fun checkPermissions(): Boolean {
        if ( checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) &&
            checkGranted(Manifest.permission.ACCESS_FINE_LOCATION) ){
            return true
        }
        return false
    }

    //checa si el gps está apagado
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    //Pedir los permisos requeridos para que funcione la localización
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            MainActivity.PERMISSION_ID
        )
    }

    private fun checkGranted(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }
}