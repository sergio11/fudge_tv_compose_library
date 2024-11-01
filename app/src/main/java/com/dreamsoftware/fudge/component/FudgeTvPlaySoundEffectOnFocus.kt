package com.dreamsoftware.fudge.component

import android.content.Context
import android.media.AudioManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext

@Composable
fun Modifier.fudgeTvPlaySoundEffectOnFocus(
    effectType: Int = AudioManager.FX_FOCUS_NAVIGATION_UP
): Modifier {
    val context = LocalContext.current
    val audioManager = remember {
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }
    return this.onFocusChanged {
        if (it.isFocused) {
            audioManager.playSoundEffect(effectType)
        }
    }
}