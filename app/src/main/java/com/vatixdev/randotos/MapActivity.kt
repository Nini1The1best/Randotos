package com.vatixdev.randotos

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline

class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView
    private val LOCATION_PERMISSION_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE))
        setContentView(R.layout.activity_map)

        map = findViewById(R.id.mapView)
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)

        // Vérifie permission GPS
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST
            )
        } else {
            startMap()
        }
    }

    // Gestion de la réponse à la demande de permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startMap()
            } else {
                Toast.makeText(this, "Permission GPS refusée", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startMap() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location == null) {
                Toast.makeText(this, "Impossible de récupérer la localisation", Toast.LENGTH_LONG).show()
                return@addOnSuccessListener
            }

            val start = GeoPoint(location.latitude, location.longitude)
            map.controller.setZoom(15.0)
            map.controller.setCenter(start)

            // Récupère steps et amplitude
            val steps = intent.getIntExtra("steps", 6).coerceAtLeast(1)
            val amplitude = intent.getDoubleExtra("amplitude", 0.001).coerceAtLeast(0.001)

            // Génère un chemin
            val path = PathGenerator.generatePath(start, steps, amplitude)

            val polyline = Polyline()
            polyline.setPoints(path)
            map.overlays.add(polyline)
            map.invalidate()
        }
    }
}