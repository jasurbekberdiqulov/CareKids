package com.example.childapp.remote.model

data class CallLogs(val clientID: String, val callList: ArrayList<CallDetails>?)

data class CallDetails(
    val callType: String?,
    val callDayTime: String?,
    val callDuration: String?,
    val phoneNumber: String?,
    var name: String?
)
