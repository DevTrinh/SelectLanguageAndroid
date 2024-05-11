package com.fftools.selectlanguage

import com.fftools.selectlanguage.language.Language
import com.fftools.selectlanguage.language.LanguageImpl
import com.fftools.selectlanguage.language.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object AppModule {
    val appModule = module {

        single<Language> { LanguageImpl(get()) }

        viewModel { LanguageViewModel(get()) }
    }
}
