package com.randotos.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import com.randotos.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            val steps = binding.stepsInput.text.toString().toIntOrNull() ?: 0
            val amplitude = binding.amplitudeInput.text.toString().toDoubleOrNull() ?: 0.0

            val intent = Intent(this, MapActivity::class.java).apply {
                putExtra("steps", steps)
                putExtra("amplitude", amplitude)

            // Passe les paramètres à MapActivity
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("steps", steps)
            intent.putExtra("amplitude", amplitude)
            startActivity(intent)
        }
    }
}
