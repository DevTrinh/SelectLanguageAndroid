package com.fftools.selectlanguage.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fftools.selectlanguage.utils.Coroutines
import com.fftools.selectlanguage.R
import com.fftools.selectlanguage.utils.AppPreferences

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity()
    }

    private fun startActivity() {
        Coroutines.delay(1000) {
            if (AppPreferences.getInstance(this).isFirstRunLanguage()) {
                startActivity(Intent(this@SplashActivity, LanguageActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }
    }
}