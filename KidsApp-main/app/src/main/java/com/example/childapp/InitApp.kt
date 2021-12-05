package com.example.childapp

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.childapp.common.Constants.LOCALE_ENGLISH
import com.example.childapp.common.Constants.LOCALE_RUSSIAN
import com.example.childapp.common.Constants.LOCALE_UZBEKISTAN
import dagger.hilt.android.HiltAndroidApp
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump

@HiltAndroidApp
class InitApp : Application() {
    companion object {
         lateinit var instance: Context

    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppLocale.supportedLocales = listOf(LOCALE_ENGLISH, LOCALE_RUSSIAN, LOCALE_UZBEKISTAN)
        // Optional: Persist changes to the desiredLocale to sharedPreferences
        AppLocale.appLocaleRepository = SharedPrefsAppLocaleRepository(this)
        ViewPump.init(RewordInterceptor)
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(AppLocale.wrap(newBase))
    }

    override fun getResources(): Resources {
        return AppLocale.wrap(baseContext).resources
    }
}