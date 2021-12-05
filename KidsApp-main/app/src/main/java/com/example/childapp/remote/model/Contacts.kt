package com.example.childapp.remote.model

data class Contacts(val clientID: String, val contactList: ArrayList<Contact>?)
data class Contact(val contactName: String, val phoneNumber: String)