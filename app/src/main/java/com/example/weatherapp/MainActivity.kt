package com.example.weatherapp
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.Locale

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

        findViewById<Button>(R.id.btn_toggle).apply {
            text = getString(R.string.btn_toggle_label)
            setOnClickListener {
                showToggleDialog()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_language -> {
                showLanguageDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToggleDialog() {
        val options = arrayOf(getString(R.string.brief_view), getString(R.string.detailed_view))
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.choose_weather_view))
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

    private fun showLanguageDialog() {
        val languages = arrayOf("English", "Русский") // Определяем доступные языки
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Language")
            .setItems(languages) { _, which ->
                when (which) {
                    0 -> switchLanguage("en") // Для английского языка
                    1 -> switchLanguage("ru") // Для русского языка
                }
            }
        builder.create().show()
    }

    private fun switchLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        recreate() // Пересоздаем активити для применения изменений
    }
}
