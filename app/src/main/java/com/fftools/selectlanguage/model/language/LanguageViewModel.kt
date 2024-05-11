package com.fftools.selectlanguage.model.language

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fftools.selectlanguage.model.language.ItemLanguage
import com.fftools.selectlanguage.model.language.Language

class LanguageViewModel(private val language: Language) : ViewModel() {
    private val _listLanguage = MutableLiveData<MutableList<ItemLanguage>>()
    val listLanguageState: LiveData<MutableList<ItemLanguage>> get() = _listLanguage

    fun loadCountries(): MutableList<ItemLanguage> {
        return language.getAllLanguage()
    }

    fun searchCountries(query: String) {
        _listLanguage.value = language.queryLanguage(query)
    }

    fun setUpLanguage(value: String) {
        language.setPrefLanguage(value)
    }

    fun setFirstLanguageRun() {
        language.setPrefFirstLanguageRun()
    }

}