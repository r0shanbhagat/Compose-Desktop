package com.myapp.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter

/**
 * @Details :AppUtils
 * @Author Roshan Bhagat
 */

@Composable
internal fun rememberBitmapResource(path: String): Painter {
    return remember(path) { BitmapPainter(readResourceBytes(path).decodeToImageBitmap()) }
}

private object ResourceLoader

private fun readResourceBytes(resourcePath: String) =
    ResourceLoader.javaClass.classLoader.getResourceAsStream(resourcePath).readAllBytes()
