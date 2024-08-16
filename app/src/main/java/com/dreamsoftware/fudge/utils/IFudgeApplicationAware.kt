package com.dreamsoftware.fudge.utils

import androidx.annotation.StringRes

interface IFudgeApplicationAware {

    fun getString(@StringRes stringResId: Int): String

    fun getString(@StringRes stringResId: Int, vararg args: Any): String
}