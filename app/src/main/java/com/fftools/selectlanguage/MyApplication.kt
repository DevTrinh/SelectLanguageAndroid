package com.fftools.selectlanguage

import android.app.Application
import com.fftools.selectlanguage.di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}