package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private var isBriefView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BriefWeatherFragment())
                .commit()
        }

        findViewById<Button>(R.id.btn_toggle).setOnClickListener {
            toggleFragment()
        }
    }

    private fun toggleFragment() {
        val fragment: Fragment = if (isBriefView) {
            DetailedWeatherFragment()
        } else {
            BriefWeatherFragment()
        }
        isBriefView = !isBriefView
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
