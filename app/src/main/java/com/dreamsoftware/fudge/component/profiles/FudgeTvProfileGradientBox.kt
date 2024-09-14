package com.dreamsoftware.fudge.component.profiles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.tv.material3.MaterialTheme

@Composable
fun FudgeTvProfileGradientBox(
    mainColor: Color? = null,
    secondaryColor: Color? = null,
    ternaryColor: Color? = null,
    content: @Composable BoxScope.() -> Unit
) {
    with(MaterialTheme.colorScheme) {
        val gradient = Brush.radialGradient(
            0.33f to (mainColor ?: primary),
            0.66f to (secondaryColor ?: secondaryContainer),
            1.0f to (ternaryColor ?: primaryContainer),
            radius = 1500.0f,
            tileMode = TileMode.Repeated
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient),
            content = content
        )
    }
}