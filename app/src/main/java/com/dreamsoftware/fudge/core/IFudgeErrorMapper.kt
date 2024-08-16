package com.dreamsoftware.fudge.core

interface IFudgeErrorMapper {
    fun mapToMessage(ex: Throwable): String
}