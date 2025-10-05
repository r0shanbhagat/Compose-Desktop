package com.myapp.data.network

import io.ktor.http.HttpStatusCode

/**
 * @Details BaseResponse class for All the response
 */
abstract class BaseResponse {
    var bodyText: String? = ""
    var statusCode = 0
    var statusMessage: String? = ""
    var headers: Map<String, List<String>> = emptyMap()

    fun success(): Boolean {
        return statusCode == HttpStatusCode.OK.value ||
                statusCode == HttpStatusCode.Accepted.value ||
                statusCode == HttpStatusCode.Created.value
    }
}