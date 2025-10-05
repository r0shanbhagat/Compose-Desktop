package com.myapp.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * @Details :AppUtils
 * @Author Roshan Bhagat
 */

@Composable
internal fun rememberBitmapResource(path: String): Painter {
    return remember(path) { BitmapPainter(readResourceBytes(path).decodeToImageBitmap()) }
}

private object ResourceLoader

private fun readResourceBytes(resourcePath: String): ByteArray {
    val stream = ResourceLoader.javaClass.classLoader.getResourceAsStream(resourcePath)
    checkNotNull(stream) { "Resource not found: $resourcePath" }
    return stream.use { it.readBytes() }
}

fun <T> getKoinInstance(): T {
    return object : KoinComponent {}.get()
}

