package com.vatixdev.randotos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var steps: Int = 6
    private var amplitude: Double = 0.001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Récupération des extras
        steps = intent.getIntExtra("steps", 6)
        amplitude = intent.getDoubleExtra("amplitude", 0.001)

        // Initialiser la carte
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val start = LatLng(48.8566, 2.3522) // Paris par défaut
        mMap.addMarker(MarkerOptions().position(start).title("Départ"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 14f))

        // Ici tu pourras générer ton chemin aléatoire avec steps + amplitude
    }
}