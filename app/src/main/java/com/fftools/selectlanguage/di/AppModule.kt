package com.fftools.selectlanguage.di

import com.fftools.selectlanguage.model.language.Language
import com.fftools.selectlanguage.model.language.LanguageImpl
import com.fftools.selectlanguage.model.language.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object AppModule {
    val appModule = module {

        single<Language> { LanguageImpl(get()) }

        viewModel { LanguageViewModel(get()) }
    }
}
