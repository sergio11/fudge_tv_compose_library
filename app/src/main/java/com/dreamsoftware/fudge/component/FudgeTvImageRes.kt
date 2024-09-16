package com.dreamsoftware.fudge.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun FudgeTvImageRes(
    modifier: Modifier = Modifier,
    tint: Color? = null,
    @DrawableRes imageRes: Int,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = modifier,
        colorFilter = tint?.let(ColorFilter::tint),
        contentScale = contentScale
    )
}