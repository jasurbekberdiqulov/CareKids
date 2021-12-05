package com.example.childapp.remote.model

data class Sim(
    val clientID: String,
    val deviceID: String,
    val operatorName: String?,
    val serialNumber: String?,
    val countryISO: String?,
    val callState: String?
)
