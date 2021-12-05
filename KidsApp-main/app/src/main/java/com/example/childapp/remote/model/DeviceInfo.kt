package com.example.childapp.remote.model

data class DeviceInfo(
    val clientID:String?,
    val verRel:String?,
    val product:String?,
    val model:String?,
    val brand:String?,
    val device:String?,
    val host:String?,
    val time:String,
    val freeMemory:String?,
    val totalMemory:String?,
    val ipAddress:String?,
    val macAddress:String?
)