package com.fftools.selectlanguage.language

interface Language {
    fun getAllLanguage(): MutableList<ItemLanguage>
    fun queryLanguage(value: String): MutableList<ItemLanguage>
    fun setPrefLanguage(language: String)
    fun setPrefFirstLanguageRun()
}