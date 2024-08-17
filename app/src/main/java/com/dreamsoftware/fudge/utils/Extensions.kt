package com.dreamsoftware.fudge.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Duration

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '

fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow

fun Number.padStartWith0(): String = this.toString().padStart(2, '0')

fun Duration.toVideoTextProgress() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m.padStartWith0()}:${s.padStartWith0()}"
    } else {
        "${m.padStartWith0()}:${s.padStartWith0()}"
    }
}

fun Duration.toVideoTextDuration() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m.padStartWith0()}:${s.padStartWith0()}"
    } else {
        "${m.padStartWith0()}:${s.padStartWith0()}"
    }
}


fun Duration.toAudioTextProgress() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m}:${s.padStartWith0()}"
    } else {
        "${m}:${s.padStartWith0()}"
    }
}

fun Duration.toAudioTextDuration() = toComponents { h, m, s, _ ->
    if (h > 0) {
        "$h:${m}:${s.padStartWith0()}"
    } else {
        "${m}:${s.padStartWith0()}"
    }
}

fun Context.restartApplication() {
    packageManager.getLeanbackLaunchIntentForPackage(packageName)?.run {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }?.let(::startActivity)
}

fun Context.openSystemSettings() {
    startActivity(Intent(Settings.ACTION_SETTINGS))
}