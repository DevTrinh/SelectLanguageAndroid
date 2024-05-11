package com.fftools.selectlanguage.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.Locale

class AppPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREF_NAME = "pref_name"
        const val PREF_IS_FIRST_RUN_LANGUAGE = "PREF_IS_FIRST_RUN_LANGUAGE"
        const val PREF_LANGUAGE_VALUE = "language_value"

        @Volatile
        private var instance: AppPreferences? = null
        fun getInstance(context: Context): AppPreferences {
            return instance ?: synchronized(this) {
                instance ?: AppPreferences(context).also { instance = it }
            }
        }
    }

    fun isFirstRunLanguage(): Boolean {
        return sharedPreferences.getBoolean(PREF_IS_FIRST_RUN_LANGUAGE, true)
    }

    fun setFirstRunLanguage() {
        sharedPreferences.edit().putBoolean(PREF_IS_FIRST_RUN_LANGUAGE, false).apply()
    }

    fun setLanguage(code: String) {
        sharedPreferences.edit().putString(PREF_LANGUAGE_VALUE, code).apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(PREF_LANGUAGE_VALUE, Locale.getDefault().language)
            .toString()
    }
}
