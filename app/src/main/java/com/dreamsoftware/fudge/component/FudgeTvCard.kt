package com.dreamsoftware.fudge.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage

@Composable
fun FudgeTvCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageUrl: String,
    title: String,
    subtitle: String,
    cardAspectRatio: Float = 16f / 9f
) {
    with(MaterialTheme.colorScheme) {
        Column(
            modifier = Modifier
                .size(width = 196.dp, height = 158.25.dp)
        ) {
            Card(
                onClick = onClick,
                modifier = modifier
                    .aspectRatio(cardAspectRatio)
                    .background(Color.Transparent, RoundedCornerShape(16.dp))
                    .padding(bottom = 8.dp),
                border = CardDefaults.border(
                    border = Border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = onPrimary
                        )
                    ),
                    focusedBorder = Border(
                        border = BorderStroke(
                            width = 3.dp,
                            color = border
                        )
                    )
                ),
            ) {
                AsyncImage(
                    model = imageUrl, contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .height(110.25.dp)
                        .aspectRatio(cardAspectRatio),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                )
            }
            FudgeTvText(
                type = FudgeTvTextTypeEnum.LABEL_LARGE,
                titleText = title
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                FudgeTvText(
                    modifier = Modifier.padding(end = 4.dp),
                    type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
                    titleText = subtitle
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomCard() {
    FudgeTvCard(
        imageUrl = "",
        subtitle = "30 min | Yoga",
        title = "Yoga for Beginners",
        onClick = {},
    )
}