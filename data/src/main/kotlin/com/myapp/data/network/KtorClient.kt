package com.myapp.data.network

import com.myapp.data.network.Util.json
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json

object KtorClient {

    val httpClient: HttpClient = HttpClient(CIO) {
        expectSuccess = false
        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }
        install(ContentEncoding) {
            deflate(1.0F)
            gzip(0.9F)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json
        }
    }


    fun String.isJson(): Boolean {
        return try {
            Json.parseToJsonElement(this) // will throw if invalid
            true
        } catch (_: Exception) {
            false
        }
    }
}