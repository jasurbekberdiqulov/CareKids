package com.example.childapp.common

//sealed class Status<T>(val data: T?, val message: String?) {
//    class Success<T>(data: T) : Status<T>(data, null)
//    class Error<T>(message: String) : Status<T>(null, message)
//
//
//
//}

sealed class Status<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Status<T>(data)
    class Loading<T>(data: T? = null) : Status<T>(data)
    class Error<T>(message: String, data: T? = null) : Status<T>(data, message)
}
