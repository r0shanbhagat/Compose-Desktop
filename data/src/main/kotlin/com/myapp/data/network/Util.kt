package com.myapp.data.network

import com.myapp.data.network.ApiConstants.BASE_URL
import com.toxicbakery.logging.Arbor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class NetworkException(val errorCode: Int, val errorMsg: String) : RuntimeException(errorMsg)

object Util {

    @OptIn(ExperimentalSerializationApi::class)
    val json by lazy {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
            allowSpecialFloatingPointValues = true
        }
    }

    inline fun <reified T> decodeFromString(jsonInString: String): T =
        json.decodeFromString(jsonInString)

    inline fun <reified T> encodeToString(obj: T): String =
        json.encodeToString(Json.serializersModule.serializer(), obj)

    suspend inline fun <T : BaseResponse> executeApiCall(
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
        callback: ApiCallback<T>? = null,
        crossinline block: suspend CoroutineScope.() -> T?,
    ): T? {
        var response: T? = null
        try {
            withContext(ioDispatcher) {
                response = block()
                if (response?.success() == true) callback?.onSuccess(response) else callback?.onError(
                    RuntimeException("oops Something went wrong. Try again later!!")
                )
            }
        } catch (e: Exception) {
            Arbor.e(e.message.toString())
            callback?.onError(e)
        } finally {
            callback?.onComplete()
        }
        return response
    }

    suspend inline fun <reified T : BaseResponse> executeGetApiCall(path: String): T? {
        return executeApiCall {
            val request = AppRequest(path)
            NetworkClient(BASE_URL).get(request)
        }
    }

    fun List<Any>?.isListNotEmpty(): Boolean {
        return this != null && isNotEmpty()
    }

}
