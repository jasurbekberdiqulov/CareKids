package com.example.childapp.db.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child_age")
data class Child(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var childAge: String,
    var accept: Boolean
)


@Entity(tableName = "code")
data class Code(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var password: String
)

@Entity(tableName = "error_apps")
data class ErrorApp(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var className: String?,
    var errorMsg: String? = null,
    var date: String? = null
)

@Entity(tableName = "send_dates")
data class SendDate(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var moduleName: String,
    var moduleSendDate: String
)



@Entity(tableName = "apps")
data class App(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val appName: String,
    val packageName: String,

    val statusApp: String = "unlock",
    val updateField: Boolean = false
)






