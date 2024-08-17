package com.dreamsoftware.fudge.component.player.video

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.dreamsoftware.fudge.component.player.FudgeTvPlayerControllerIndicator
import com.dreamsoftware.fudge.theme.FudgeTvTheme

@Composable
fun RowScope.FudgeTvVideoPlayerControllerIndicator(
    progress: Float,
    onSeek: (seekProgress: Float) -> Unit,
    state: FudgeTvVideoPlayerState,
    modifier: Modifier = Modifier,
) {
    var isSelected by remember { mutableStateOf(false) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            state.showControls(seconds = Int.MAX_VALUE)
        } else {
            state.showControls()
        }
    }

    FudgeTvPlayerControllerIndicator(
        progress = progress,
        onSeek = onSeek,
        isSelected = isSelected,
        onSelected = { isSelected = !isSelected },
        modifier = modifier.weight(1f)
    )
}


@Preview(device = Devices.TV_1080p)
@Composable
fun PreviewVideoPlayerControllerIndicator() {
    FudgeTvTheme {
        Row {
            FudgeTvVideoPlayerControllerIndicator(
                progress = 0.1f,
                onSeek = {},
                state = rememberVideoPlayerState()
            )
        }
    }
}