package com.dreamsoftware.fudge.component.player.audio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.theme.FudgeTvTheme

@Composable
fun FudgeTvAudioPlayerDurationText(
    textDuration: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.60f)
) {
    FudgeTvText(
        modifier = modifier,
        type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
        titleText = textDuration,
        textColor = color.copy(alpha = 0.60f)
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewAudioPlayerControllerText() {
    FudgeTvTheme {
        FudgeTvAudioPlayerDurationText(textDuration = "3:06")
    }
}