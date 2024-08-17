package com.dreamsoftware.fudge.component.player.audio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.fudge.component.player.FudgeTvPlayerControllerIndicator

@Composable
fun FudgeTvAudioPlayerControllerIndicator(
    progress: Float,
    onSeek: (seekProgress: Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isSelected by remember { mutableStateOf(false) }
    FudgeTvPlayerControllerIndicator(
        progress = progress,
        onSeek = onSeek,
        isSelected = isSelected,
        onSelected = { isSelected = !isSelected },
        modifier = modifier
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewAudioPlayerControllerIndicator() {
    FudgeTvAudioPlayerControllerIndicator(progress = 1f, {})
}