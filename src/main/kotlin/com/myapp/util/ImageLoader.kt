package com.myapp.util

/**
 * @Details :ImageLoader.kt
 * @Author Roshan Bhagat
 */

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.network.okhttp.OkHttpNetworkFetcherFactory

// Create a singleton ImageLoader for the entire application
fun getAsyncImageLoader(context: PlatformContext): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            // Add a Ktor-based network fetcher for fetching images from URLs
            add(OkHttpNetworkFetcherFactory())
        }
        .build()
}