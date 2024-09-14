package com.dreamsoftware.fudge.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import com.dreamsoftware.fudge.theme.FudgeTvTheme
import com.dreamsoftware.fudge.utils.EMPTY

@Composable
fun FudgeTvCardDetails(
    modifier: Modifier = Modifier,
    title: String = String.EMPTY,
    subtitle: String = String.EMPTY,
    description: String = String.EMPTY,
    imageUrl: String = String.EMPTY
) {
    with(MaterialTheme.colorScheme) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Image",
                modifier =  Modifier
                    .size(width = 268.dp, height = 150.dp)
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
            FudgeTvText(
                type = FudgeTvTextTypeEnum.TITLE_MEDIUM,
                titleText = title,
                textColor = onSurface,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
            FudgeTvText(
                type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
                titleText = subtitle,
                textColor = onSurface.copy(alpha = 0.6f),
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
            FudgeTvText(
                type = FudgeTvTextTypeEnum.BODY_SMALL,
                titleText = description,
                textColor = onSurfaceVariant.copy(alpha = 0.8f),
                maxLines = 4,
                overflow = TextOverflow.Clip
            )
        }
    }
}

@Preview
@Composable
private fun CommonCardDetailsPreview() {
    FudgeTvTheme {
        FudgeTvCardDetails(
            title = "Total-body balance pilates",
            subtitle = "34 Min  |  Intensity ••••",
            description = "Andrea's signature low-impact, total-body class in just 30 minutes. Hit every muscle group with barre and Pilates moves that leave you feeling strong, refreshed, and energized",
            imageUrl = "URL"
        )
    }
}