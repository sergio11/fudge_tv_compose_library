package com.dreamsoftware.fudge.component.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.theme.FudgeTvTheme

@Composable
fun FudgeTvPlayerTitle(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    descriptionModifier: Modifier = Modifier,
    titleColor: Color? = null,
    descriptionColor: Color? = null
) {
    with(MaterialTheme.colorScheme) {
        Column(modifier) {
            FudgeTvText(
                modifier = modifier.padding(bottom = 4.dp),
                type = FudgeTvTextTypeEnum.HEADLINE_SMALL,
                titleText = title,
                textColor = titleColor ?: onSurface,
            )
            FudgeTvText(
                modifier = descriptionModifier,
                type = FudgeTvTextTypeEnum.LABEL_LARGE,
                titleText = description,
                textColor = (descriptionColor ?: onSurface).copy(0.60f),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewPlayerTitle() {
    FudgeTvTheme {
        FudgeTvPlayerTitle(title = "Battle ropes HIIT", description = "Hugo Wright")
    }
}