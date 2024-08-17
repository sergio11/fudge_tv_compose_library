package com.dreamsoftware.fudge.core

interface IFudgeTvErrorMapper {
    fun mapToMessage(ex: Throwable): String
}