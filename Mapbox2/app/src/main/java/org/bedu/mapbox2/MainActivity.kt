package org.bedu.mapbox2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

class MainActivity : AppCompatActivity(), OnMapReadyCallback, PermissionsListener {

    companion object {
        const val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
        const val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 2
    }

    private  lateinit var mapView: MapView
    private lateinit var map: MapboxMap
    private lateinit var permissionsManager: PermissionsManager
    private lateinit var locationEngine: LocationEngine
    private lateinit var callback: LocationChangeListeningCallback
    private lateinit var button: Button
    private lateinit var buttonStop: Button

    var estate=false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_main)
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        initComponents()
        buttonsListeners()
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        map = mapboxMap
        callback = LocationChangeListeningCallback()
        mapboxMap.setStyle(Style.MAPBOX_STREETS) {
            enableLocationComponent(it)

        }

    }


    private fun enableLocationComponent(loadedMapStyle: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(this, loadedMapStyle)
                .useDefaultLocationEngine(false)
                .build()
            map.locationComponent.apply {
                activateLocationComponent(locationComponentActivationOptions)
                if (ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                isLocationComponentEnabled = true                       // Enable to make component visible
                cameraMode = CameraMode.TRACKING                        // Set the component's camera mode
                renderMode = RenderMode.COMPASS                         // Set the component's render mode
            }
            initLocationEngine()
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }
    }

    private fun initLocationEngine() {

        locationEngine = LocationEngineProvider.getBestLocationEngine(this)
        val request = LocationEngineRequest
            .Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_LOW_POWER)
            .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME)
            .build()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationEngine.requestLocationUpdates(request, callback, mainLooper)
        locationEngine.getLastLocation(callback)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private inner class LocationChangeListeningCallback :
        LocationEngineCallback<LocationEngineResult> {

        override fun onSuccess(result: LocationEngineResult?)  {
            result?.lastLocation ?: return
            if (estate){
                if (result.lastLocation != null){
                    val lat = result.lastLocation?.latitude!!
                    val lng = result.lastLocation?.longitude!!

                    val latLng = LatLng(lat, lng)

                    if (result.lastLocation != null) {

                        map.locationComponent.forceLocationUpdate(result.lastLocation)
                        val position = CameraPosition.Builder()
                            .target(latLng)
                            .zoom(18.0)
                            .tilt(12.0)
                            .build()
                        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
                        Toast.makeText(applicationContext, "Latitud: $lat Longitud: $lng ", Toast.LENGTH_SHORT).show()

                    }
                }
            }else{
                if (result.lastLocation != null){
                    val lat = result.lastLocation?.latitude!!
                    val lng = result.lastLocation?.longitude!!

                    val latLng = LatLng(lat, lng)

                    if (result.lastLocation != null) {
                        map.locationComponent.forceLocationUpdate(result.lastLocation)
                        val position = CameraPosition.Builder()
                            .target(latLng)
                            .zoom(18.0)
                            .tilt(12.0)
                            .build()
                        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
                    }
                }
            }

        }

        override fun onFailure(exception: Exception) {}
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
       // Toast.makeText(this, "Necesita permisos para utilizar la app", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            map.getStyle {
                enableLocationComponent(it)
            }
        } else {
            //Solicita los permisos en caso de no aceptar
            onMapReady(map)
        }
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationEngine.removeLocationUpdates(callback)
        mapView.onDestroy()
    }

    private fun initComponents(){
        button = findViewById(R.id.button)
        buttonStop = findViewById(R.id.buttonStop)
        buttonStop.visibility = View.GONE
    }

    private fun buttonsListeners(){
        buttonStop.setOnClickListener {
            buttonStop.visibility = View.GONE
            button.visibility = View.VISIBLE
            estate=false
        }

        button.setOnClickListener {
            button.visibility = View.GONE
            buttonStop.visibility = View.VISIBLE
            estate=true

        }
    }

}