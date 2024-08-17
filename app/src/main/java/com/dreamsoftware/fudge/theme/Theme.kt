package com.dreamsoftware.fudge.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.tv.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.tv.material3.ColorScheme
import androidx.tv.material3.Shapes
import androidx.tv.material3.Typography

@Composable
fun FudgeTvTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lightColorScheme: ColorScheme = FudgeLightColorScheme,
    darkColorScheme: ColorScheme = FudgeDarkColorScheme,
    typography: Typography = DefaultTypography,
    shapes: Shapes = DefaultShapes,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}