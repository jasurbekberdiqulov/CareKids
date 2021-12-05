package com.example.childapp.db.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.childapp.common.Constants.LANGUAGE

class PreferencesManager(
    private val context: Context
) {
    private val preferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            context
        )
    }

    var language by PreferencesDelegate(preferences, LANGUAGE, "en")


}