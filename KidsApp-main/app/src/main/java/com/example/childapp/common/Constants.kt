package com.example.childapp.common

import java.util.*

object Constants {
    //api url
    const val FULL_URL = "http://195.158.18.236:55555/?clientID="

    //location settings
    const val FASTEST_LOCATION_INTERVAL = 60 * 2000L
    const val LOCATION_INTERVAL = 60 * 1000L

    //database
    const val DATABASE_NAME = "myDb"

    //language
    const val LANGUAGE = "language"
    val LOCALE_ENGLISH: Locale = Locale.ENGLISH
    val LOCALE_RUSSIAN = Locale("ru")
    val LOCALE_UZBEKISTAN = Locale("uz")



    // Timer
    const val TIMER_UPDATE_INTERVAL = 50L


    // Notifications
    const val NOTIFICATION_CHANNEL_ID = "tracking_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Tracking"
    const val NOTIFICATION_ID = 1

    // Service Intent Actions
    const val ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT"
    const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_SERVICE"
    const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

}