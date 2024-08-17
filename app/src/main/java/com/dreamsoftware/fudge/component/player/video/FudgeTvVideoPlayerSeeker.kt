package com.dreamsoftware.fudge.component.player.video

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamsoftware.fudge.theme.FudgeTvTheme
import com.dreamsoftware.fudge.utils.toVideoTextDuration
import com.dreamsoftware.fudge.utils.toVideoTextProgress
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun FudgeTvVideoPlayerSeeker(
    state: FudgeTvVideoPlayerState,
    onSeek: (Float) -> Unit,
    contentProgress: Duration,
    contentDuration: Duration,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        FudgeTvVideoPlayerControllerIndicator(
            progress = (contentProgress / contentDuration).toFloat(), onSeek = onSeek, state = state
        )
        FudgeTvVideoPlayerDurationText(
            modifier = Modifier.padding(
                horizontal = 12.dp
            ),
            textProgress = contentProgress.toVideoTextProgress(),
            textDuration = contentDuration.toVideoTextDuration()
        )
    }
}


@Preview
@Composable
fun PreviewVideoPlayerSeeker() {
    FudgeTvTheme {
        FudgeTvVideoPlayerSeeker(
            state = rememberVideoPlayerState(),
            onSeek = {},
            contentProgress = (5L).milliseconds,
            contentDuration = (50L).milliseconds
        )
    }
}