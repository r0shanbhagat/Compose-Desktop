package com.myapp.data.network

import com.myapp.data.network.KtorClient.httpClient
import com.myapp.data.network.KtorClient.isJson
import com.toxicbakery.logging.Arbor
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.io.IOException


class NetworkClient(private var requestUrl: String) {

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    @Throws(IOException::class)
    suspend inline fun <reified T : BaseResponse> get(request: BaseRequest): T? {
        requestUrl += request.path()

        val connection: HttpResponse = httpClient.get(requestUrl) {
            request.addHeaders(request.headers)
            addHeaders(request, this)
        }

        return parseResponse<T>(connection)
    }


    /**
     * addHeaders: Add headers to HttpRequestBuilder.
     */
    fun addHeaders(request: BaseRequest, connection: HttpRequestBuilder) {
        val builder = StringBuilder()
        request.headers.entries.forEach { (key, value) ->
            connection.headers.append(key, value)
            builder.append(key).append(":").append(value).append("\n")
        }
    }

    /**
     * parseResponse: It'll parse the Response received from server:
     */
    suspend inline fun <reified T : BaseResponse> parseResponse(
        connection: HttpResponse?,
    ): T? {
        if (null == connection) return null
        var response: T? = null

        println("Connection status ${connection.status}")
        /**
         * On Response Success
         */
        if (connection.status.isSuccess()) {
            val responseBodyText = connection.bodyAsText()
            // Checking Empty Response
            if (!responseBodyText.isJson()) connection.handleNetworkError("Empty Response from server.")

            Arbor.d("Response: $responseBodyText")
            // Converting Response String to Response Model Object
            response = Util.decodeFromString<T>(responseBodyText)
            response.apply {
                bodyText = responseBodyText
                statusCode = connection.status.value
                statusMessage = connection.status.description
                headers = mutableMapOf<String, List<String>>().apply {
                    connection.headers.entries().map {
                        put(it.key, it.value)
                    }
                }
            }
        } else {
            connection.handleNetworkError(connection.status.description)
        }
        return response
    }

    fun HttpResponse.handleNetworkError(errorMsg: String) {
        throw NetworkException(this.status.value, errorMsg)
    }


    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    @Throws(IOException::class)
    suspend inline fun <reified T> getList(request: BaseRequest): List<T>? {
        requestUrl += request.path()

        val connection: HttpResponse = httpClient.get(requestUrl) {
            request.addHeaders(request.headers)
            addHeaders(request, this)
        }

        // Call a new parsing function designed for lists
        return parseListResponse<T>(connection)
    }


    /**
     * parseListResponse: It'll parse a list Response received from the server.
     */
    suspend inline fun <reified T> parseListResponse(
        connection: HttpResponse?,
    ): List<T>? {
        if (null == connection) return null
        var response: List<T>? = null

        /**
         * On Response Success
         */
        if (connection.status.isSuccess()) {
            val responseBodyText = connection.bodyAsText()
            Arbor.d("Response: $responseBodyText")
            if (!responseBodyText.isJson()) {
                connection.handleNetworkError("Empty or invalid JSON array from server.")
                return null // handleNetworkError throws, but for clarity
            }

            // Key Change: Decode the string into a List<T>
            response = Util.decodeFromString<List<T>>(responseBodyText)

        } else {
            connection.handleNetworkError(connection.status.description)
        }
        return response
    }


}

