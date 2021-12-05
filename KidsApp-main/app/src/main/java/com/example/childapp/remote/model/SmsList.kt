package com.example.childapp.remote.model

data class SmsList(
    val clientID: String,
    val smsList: List<Sms>
)

data class Sms(
    val address: String? = null,
    val msg: String? = null,
    val date: String? = null,
    val type: String? = null
)