package com.example.surfit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BlueBlack,
    primaryVariant = BlueBlack,
    secondary = BlueBlack
)

private val LightColorPalette = lightColors(
    primary = BlueBlack,
    primaryVariant = BlueBlack,
    secondary = BlueBlack,
    background = Background,
    onBackground = BlueBlack
)

@Composable
fun SurfItTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = LightColorPalette
    MaterialTheme(colors = colors, content = content, typography = Typography)
}