package com.myapp.data.network


interface ApiCallback<T> {

    fun onSuccess(model: T)

    fun onError(e: Exception? = null)

    fun onComplete() {}
}