package com.dreamsoftware.fudge.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

val String.Companion.EMPTY: String
    get() = ""

val Char.Companion.SPACE: Char
    get() = ' '

fun <T> StateFlow<T>.toMutable() = this as MutableStateFlow