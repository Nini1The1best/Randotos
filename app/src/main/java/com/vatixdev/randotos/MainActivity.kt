package com.vatixdev.randotos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vatixdev.randotos.databinding.ActivityMainBinding

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
            }
            startActivity(intent)
        }
    }
}
