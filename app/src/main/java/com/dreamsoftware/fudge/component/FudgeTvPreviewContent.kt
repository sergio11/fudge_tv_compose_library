package com.dreamsoftware.fudge.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.tv.material3.MaterialTheme

@Composable
fun FudgeTvPreviewContent(
    imageUrl: String? = null,
    @DrawableRes imageRes: Int? = null,
    @DrawableRes defaultImagePlaceholderRes: Int,
    @StringRes mainTitleRes: Int,
    @StringRes secondaryTitleRes: Int,
    onBuildContent: @Composable ColumnScope.() -> Unit,
    onBuildActions: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .semantics { contentDescription = "Content Screen" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        PreviewContentImage(
            imageUrl = imageUrl,
            imageRes = imageRes,
            defaultImagePlaceholderRes = defaultImagePlaceholderRes
        )
        Spacer(modifier = Modifier.width(28.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            PreviewContentHeadline(
                mainTitleRes = mainTitleRes,
                secondaryTitleRes = secondaryTitleRes,
            )
            onBuildContent()
            PreviewContentActions(onBuildActions)
        }
    }
}

@Composable
private fun PreviewContentImage(
    imageUrl: String? = null,
    @DrawableRes imageRes: Int? = null,
    @DrawableRes defaultImagePlaceholderRes: Int,
) {
    val modifier = Modifier
        .width(325.dp)
        .fillMaxHeight(0.9f)
        .border(
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(16.dp)
        )
        .clip(RoundedCornerShape(16.dp))

    imageRes?.let {
        FudgeTvImageRes(
            modifier = modifier,
            imageRes = it,
            contentScale = ContentScale.Crop
        )
    }

    imageUrl?.let {
        FudgeTvAsyncImage(
            modifier = modifier,
            imageUrl = it,
            context = LocalContext.current,
            defaultImagePlaceholderRes = defaultImagePlaceholderRes
        )
    }
}

@Composable
private fun PreviewContentHeadline(
    @StringRes mainTitleRes: Int,
    @StringRes secondaryTitleRes: Int
) {
    with(MaterialTheme.colorScheme) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FudgeTvText(
                type = FudgeTvTextTypeEnum.TITLE_LARGE,
                textColor = onSurface,
                titleRes = mainTitleRes
            )
            FudgeTvText(
                titleRes = secondaryTitleRes,
                type = FudgeTvTextTypeEnum.BODY_SMALL,
                textColor = onSurface
            )
        }
    }
}

@Composable
private fun PreviewContentActions(
    onBuildActions: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        onBuildActions()
    }
}