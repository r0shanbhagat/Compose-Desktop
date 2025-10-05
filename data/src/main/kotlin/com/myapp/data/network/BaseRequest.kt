package com.myapp.data.network

abstract class BaseRequest() {
    var headers: HashMap<String, String> = hashMapOf()

    abstract fun path(): String?

    open fun addHeaders(headers: HashMap<String, String>) {
        headers.forEach { (key, value) ->
            this.headers[key] = value
        }
    }
}