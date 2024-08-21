package com.dreamsoftware.fudge.component.player.audio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.fudge.utils.toAudioTextDuration
import com.dreamsoftware.fudge.utils.toAudioTextProgress
import kotlin.time.Duration

@Composable
fun FudgeTvAudioPlayerSeeker(
    onSeek: (Float) -> Unit,
    contentProgress: Duration,
    contentDuration: Duration,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        FudgeTvAudioPlayerControllerIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = ( contentProgress / contentDuration).toFloat(),
            onSeek = onSeek
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FudgeTvAudioPlayerDurationText(textDuration = contentProgress.toAudioTextProgress())
            FudgeTvAudioPlayerDurationText(textDuration = contentDuration.toAudioTextDuration())
        }
    }
}