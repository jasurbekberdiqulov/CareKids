package com.example.childapp.remote.model



data class AppList(
    val clientID: String,
    val apps: ArrayList<AppInfo>?
)


data class AppInfo(
    val appName: String,
    val packageName: String,
    val versionName: String,
    val versionCode: String,
    val installedDate: String
)