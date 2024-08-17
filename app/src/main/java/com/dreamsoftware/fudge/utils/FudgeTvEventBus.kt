package com.dreamsoftware.fudge.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FudgeTvEventBus {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val _events = MutableSharedFlow<IFudgeAppEvent>()
    val events = _events.asSharedFlow()

    fun send(event: IFudgeAppEvent) {
        coroutineScope.launch {
            _events.emit(event)
        }
    }
}

interface IFudgeAppEvent