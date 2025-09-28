package com.myapp.ui.value

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.myapp.ui.value.R.color.BigStone
import com.myapp.ui.value.R.color.Elephant
import com.myapp.ui.value.R.color.PictonBlue
import com.myapp.ui.value.R.color.WildWatermelon


val LightTheme = lightColors(
    primary = PictonBlue,
    onPrimary = Color.White,
    secondary = Elephant,
    onSecondary = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    error = WildWatermelon,
    onError = Color.White,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
)

val DarkTheme = darkColors(
    primary = PictonBlue,
    onPrimary = Color.White,
    secondary = Elephant,
    onSecondary = Color.White,
    surface = BigStone,
    onSurface = Color.White,
    error = WildWatermelon,
    onError = Color.White,
    background = BigStone,
    onBackground = Color.White,
)

@Composable
fun MyAppTheme(
    isDark: Boolean = true, // TODO: If you want to support both light theme and dark theme, you'll need to implement it manually.
    content: @Composable ColumnScope.() -> Unit,
) {
    MaterialTheme(
        colors = if (isDark) DarkTheme else LightTheme,
        typography = MyAppTypography
    ) {
        Surface {
            Column {
                content()
            }
        }
    }
}