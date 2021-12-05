package com.example.childapp.remote.model

class AppStatisticList(
    val clientID: String,
    val appList: ArrayList<AppStatistics>,
    val day: Int
)

data class AppStatistics(
    var appName: String,
    var usagePercentage: Int,
    var usageDuration: String
)