package org.bedu.gps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.bedu.gps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindig:ActivityMainBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    companion object{
        const val PERMISSION_ID = 33
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindig = ActivityMainBinding.inflate(layoutInflater)

        setContentView(bindig.root)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLocation()
        bindig.btnLocate.setOnClickListener {
            getLocation()
        }

    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions()) { //verificamos si tenemos permisos
            if (isLocationEnabled()) { //localizamos sólo si el GPS está encendido
                mFusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

                    bindig.tvLatitude.text = location?.latitude.toString()
                    bindig.tvLongitude.text = location?.longitude.toString()

                }
            }
        } else{
            //si no se tiene permiso, pedirlo
            Toast.makeText(this,"Necesitas permisos de GPS para usar la app", Toast.LENGTH_LONG).show()

            requestPermissions()
        }
    }

    //Pedir los permisos requeridos para que funcione la localización
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if ( checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) &&
            checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) ){
            return true
        }
        return false
    }

    private fun checkGranted(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }


}