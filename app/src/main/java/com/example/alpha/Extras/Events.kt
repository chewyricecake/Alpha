package com.example.alpha.Extras

open class Events<T>(val response: T? = null, val msg: String? = "") {

    class Success<T>(data: T?) : Events<T>(data)
    class Loading<T>(data: T? = null) : Events<T>(data)
    class Error<T>(data: T? = null, msg: String? = null): Events<T>(data,msg)

}