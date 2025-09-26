package com.vatixdev.randotos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vatixdev.randotos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation du ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Action du bouton
        binding.startButton.setOnClickListener {
            val steps = binding.stepsInput.text.toString().toIntOrNull() ?: 6
            val amplitude = binding.amplitudeInput.text.toString().toDoubleOrNull() ?: 0.001

            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("steps", steps)
            intent.putExtra("amplitude", amplitude)
            startActivity(intent)
        }
    }
}