package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
            showToggleDialog()
        }
    }

    private fun showToggleDialog() {
        val options = arrayOf("Brief View", "Detailed View")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Weather View")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> replaceFragment(BriefWeatherFragment())
                    1 -> replaceFragment(DetailedWeatherFragment())
                }
            }
        builder.create().show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
