package com.fftools.selectlanguage.language

import android.content.Context
import com.fftools.selectlanguage.AppPreferences
import com.fftools.selectlanguage.R
import java.util.Locale

class LanguageImpl(private val context: Context) : Language {
    private lateinit var listLanguage: MutableList<ItemLanguage>
    override fun getAllLanguage(): MutableList<ItemLanguage> {
        val languages = ArrayList<ItemLanguage>().apply {
            add(ItemLanguage("Deutsch", "de", R.drawable._de))
            add(ItemLanguage("English", "en", R.drawable._us))
            add(ItemLanguage("Español", "es", R.drawable._es))
            add(ItemLanguage("Français", "fr", R.drawable._fr))
            add(ItemLanguage("Italiano", "it", R.drawable._it))
            add(ItemLanguage("日本", "ja", R.drawable._jp))
            add(ItemLanguage("한국인", "ko", R.drawable._kr))
            add(ItemLanguage("Język polski", "pl", R.drawable._pl))
            add(ItemLanguage("Português", "pt", R.drawable._pt))
            add(ItemLanguage("แบบไทย", "th", R.drawable._th))
            add(ItemLanguage("Türkçe:", "tr", R.drawable._tr))
            add(ItemLanguage("Tiếng Việt", "vi", R.drawable._vn))
            add(ItemLanguage("中国人", "zh", R.drawable._cn))
        }
        val itemSelected =
            languages.find { AppPreferences.getInstance(context).getLanguage().contains(it.code) }
        languages.remove(itemSelected)

        itemSelected?.let {
            languages.add(0, it)
        }
        listLanguage = languages
        return listLanguage
    }

    override fun queryLanguage(value: String): MutableList<ItemLanguage> {
        val result: MutableList<ItemLanguage> = mutableListOf()
        for (country in listLanguage) {
            if (country.locale.lowercase(Locale.getDefault())
                    .contains(value.lowercase(Locale.getDefault()))
            ) {
                result.add(country)
            }
        }
        return result
    }

    override fun setPrefLanguage(language: String) {
        AppPreferences.getInstance(context).setLanguage(language)
    }

    override fun setPrefFirstLanguageRun() {
        AppPreferences.getInstance(context).setFirstRunLanguage()
    }
}