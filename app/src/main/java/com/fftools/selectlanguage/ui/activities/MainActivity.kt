package com.fftools.selectlanguage.ui.activities

import android.app.LocaleManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fftools.selectlanguage.databinding.ActivityMainBinding
import com.fftools.selectlanguage.utils.AppPreferences
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.main)
        val code = AppPreferences.getInstance(this).getLanguage()
        setLocale(code)
        Toast.makeText(this, code,Toast.LENGTH_LONG).show()
    }

    private fun setLocale(localeToSet: String) {
        when (Build.VERSION.SDK_INT) {
            in (1..<Build.VERSION_CODES.N) -> {
                val locale = Locale(localeToSet)
                Locale.setDefault(locale)
                val config = Configuration()
                @Suppress("DEPRECATION")
                config.locale = locale
                @Suppress("DEPRECATION")
                resources.updateConfiguration(config, resources.displayMetrics)
            }

            in (Build.VERSION_CODES.N..<Build.VERSION_CODES.TIRAMISU) -> {
                val localeListToSet = LocaleList(Locale(localeToSet))
                LocaleList.setDefault(localeListToSet)
                resources.configuration.setLocales(localeListToSet)
                @Suppress("DEPRECATION")
                resources.updateConfiguration(resources.configuration, resources.displayMetrics)
            }

            else -> {
                getSystemService(LocaleManager::class.java).applicationLocales =
                    LocaleList.forLanguageTags(localeToSet)
            }

        }
    }
}