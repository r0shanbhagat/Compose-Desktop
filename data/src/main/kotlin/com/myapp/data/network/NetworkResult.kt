package com.myapp.data.network

sealed class NetworkResult {
    data class Success<T>(val data: T) : NetworkResult()
    data class Error<T>(val error: T? = null) : NetworkResult()
    data class Exception(val throwable: Throwable? = null) : NetworkResult()
    data object Empty : NetworkResult()
}


fun <T> NetworkResult.onSuccess(
    executable: (T) -> Unit
): NetworkResult = apply {
    if (this is NetworkResult.Success<*>) {
        executable(data as T)
    }
}


fun <T> NetworkResult.onError(
    executable: (T?) -> Unit
): NetworkResult = apply {
    if (this is NetworkResult.Error<*>) {
        executable(error as T?)
    }
}


fun NetworkResult.onException(
    executable: (Throwable?) -> Unit
): NetworkResult = apply {
    if (this is NetworkResult.Exception) {
        executable(throwable)
    }
}

fun NetworkResult.onEmpty(
    executable: () -> Unit
): NetworkResult = apply {
    if (this is NetworkResult.Empty) {
        executable()
    }
}