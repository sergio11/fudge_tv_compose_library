package com.dreamsoftware.fudge.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.dreamsoftware.fudge.core.FudgeViewModel
import com.dreamsoftware.fudge.core.SideEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun <SE: SideEffect, VM: FudgeViewModel<*, SE>>ConsumeSideEffects(
    viewModel: VM,
    lifecycle: Lifecycle,
    onSideEffectFired: (SE) -> Unit
) {
    RepeatOnStart(viewModel, lifecycle) {
        sideEffectWithReplay.collectLatest { event ->
            onSideEffectFired(event)
        }
    }
    RepeatOnStart(viewModel, lifecycle) {
        sideEffectWithoutReplay.collectLatest { event ->
            onSideEffectFired(event)
        }
    }
}

@Composable
private fun <SE: SideEffect, VM: FudgeViewModel<*, SE>> RepeatOnStart(
    viewModel: VM,
    lifecycle: Lifecycle,
    block: suspend VM.() -> Unit
) {
    LaunchedEffect(viewModel, lifecycle) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.block()
        }
    }
}