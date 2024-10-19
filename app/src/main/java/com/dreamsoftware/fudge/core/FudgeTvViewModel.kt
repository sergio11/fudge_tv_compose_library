package com.dreamsoftware.fudge.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Abstract base class for ViewModels that support UI state management and side effects.
 *
 * @param STATE The type representing the UI state.
 * @param EFFECT The type representing side effects.
 */
abstract class FudgeTvViewModel<STATE : UiState<STATE>, EFFECT : SideEffect> : ViewModel(), IFudgeTvScreenActionListener {

    private companion object {
        const val ENABLE_REPLAY_SIDE_EFFECTS = 1
        const val DISABLE_REPLAY_SIDE_EFFECTS = 0
        const val EXTRA_BUFFER_CAPACITY_SIZE = 64
    }

    // MutableStateFlow for managing UI state
    private val _uiState: MutableStateFlow<STATE> by lazy {
        MutableStateFlow(onGetDefaultState())
    }

    // Immutable StateFlow for observing UI state changes
    val uiState: StateFlow<STATE> = _uiState

    // MutableSharedFlow with replay enabled
    private val _sideEffectWithReplay: MutableSharedFlow<EFFECT> by lazy {
        MutableSharedFlow(
            replay = ENABLE_REPLAY_SIDE_EFFECTS,
            extraBufferCapacity = EXTRA_BUFFER_CAPACITY_SIZE
        )
    }

    val sideEffectWithReplay: SharedFlow<EFFECT> = _sideEffectWithReplay.asSharedFlow()

    // MutableSharedFlow without replay
    private val _sideEffectWithoutReplay: MutableSharedFlow<EFFECT> by lazy {
        MutableSharedFlow(
            replay = DISABLE_REPLAY_SIDE_EFFECTS
        )
    }

    val sideEffectWithoutReplay: SharedFlow<EFFECT> = _sideEffectWithoutReplay.asSharedFlow()

    /**
     * Provides the default UI state when the ViewModel is initialized.
     *
     * @return The default UI state.
     */
    abstract fun onGetDefaultState(): STATE

    fun onErrorAccepted() {
        updateState { it.copyState(errorMessage = null) }
    }

    /**
     * Updates the UI state using the provided reducer function.
     *
     * @param reducer A function that takes the current state and returns the new state.
     */
    protected fun updateState(reducer: (currentState: STATE) -> STATE) {
        _uiState.value = reducer(_uiState.value)
    }

    /**
     * Executes a callback function on the current UI state.
     *
     * This method allows you to perform operations on the current state of the UI by providing a lambda function
     * that will be executed in the context of the current `uiState`. The `uiState` is accessed via the `value`
     * property, and the callback is invoked on it.
     *
     * @param callback A lambda function that operates on the current `STATE`. This lambda is executed
     * within the context of the current UI state, allowing modifications or actions to be taken based on the state.
     */
    protected fun doOnUiState(callback: STATE.() -> Unit) {
        uiState.value.callback()
    }

    /**
     * Launches a side effect by emitting it to the SharedFlow.
     *
     * @param effect The side effect to be emitted.
     */
    protected fun launchSideEffect(effect: EFFECT, enableReplay: Boolean = false) {
        viewModelScope.launch {
            if (enableReplay) {
                _sideEffectWithReplay.emit(effect)
            } else {
                _sideEffectWithoutReplay.emit(effect)
            }
        }
    }

    /**
     * Executes a use case asynchronously.
     *
     * @param useCase The use case to execute.
     * @param onGetDefaultValue A function that provides a default value in case of failure.
     * @param onMapExceptionToState A function to map exceptions to the state.
     * @param showLoadingState A boolean indicating if the loading state should be shown (default is true).
     * @return The result of the use case execution.
     */
    protected suspend fun <RESULT, UC : FudgeTvUseCase<RESULT>> executeUseCase(
        useCase: UC,
        onGetDefaultValue: () -> RESULT,
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null,
        showLoadingState: Boolean = true
    ): RESULT {
        if (showLoadingState) {
            onLoading()
        }
        return try {
            useCase.invoke(scope = viewModelScope)
        } catch (ex: Exception) {
            onErrorOccurred(ex, onMapExceptionToState)
            onGetDefaultValue()
        } finally {
            if (showLoadingState) {
                onIdle()
            }
        }
    }

    /**
     * Executes a use case asynchronously.
     *
     * @param useCase The use case to execute.
     * @param onSuccess A callback function for successful execution.
     * @param onFailed A callback function for failed execution.
     * @param onMapExceptionToState A function to map exceptions to the state.
     * @param showLoadingState A boolean indicating if the loading state should be shown (default is true).
     */
    protected fun <RESULT, UC : FudgeTvUseCase<RESULT>> executeUseCase(
        useCase: UC,
        onSuccess: (RESULT) -> Unit = {},
        onFailed: () -> Unit = {},
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null,
        showLoadingState: Boolean = true
    ) {
        if (showLoadingState) {
            onLoading()
        }
        useCase.invoke(
            scope = viewModelScope,
            onSuccess = {
                if (showLoadingState) {
                    onIdle()
                }
                onSuccess(it)
            },
            onError = {
                onErrorOccurred(it, onMapExceptionToState)
                onFailed()
            }
        )
    }

    /**
     * Executes a use case with parameters asynchronously.
     *
     * @param useCase The use case to execute.
     * @param params The parameters for the use case.
     * @param onMapExceptionToState A function to map exceptions to the state.
     * @param onGetDefaultValue A function that provides a default value in case of failure.
     * @param showLoadingState A boolean indicating if the loading state should be shown (default is true).
     * @return The result of the use case execution.
     */
    protected suspend fun <PARAMS, RESULT, UC : FudgeTvUseCaseWithParams<PARAMS, RESULT>> executeUseCaseWithParams(
        useCase: UC,
        params: PARAMS,
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null,
        onGetDefaultValue: () -> RESULT,
        showLoadingState: Boolean = true
    ): RESULT {
        if (showLoadingState) {
            onLoading()
        }
        return try {
            useCase.invoke(
                scope = viewModelScope,
                params = params
            )
        } catch (ex: Exception) {
            onErrorOccurred(ex, onMapExceptionToState)
            onGetDefaultValue()
        } finally {
            if (showLoadingState) {
                onIdle()
            }
        }
    }

    /**
     * Executes a use case with parameters asynchronously, providing callbacks for success and failure.
     *
     * @param useCase The use case to execute.
     * @param params The parameters for the use case.
     * @param onSuccess A callback function to be invoked upon successful execution with the result.
     * @param onFailed A callback function to be invoked upon failed execution.
     * @param onMapExceptionToState A function to map exceptions to the state.
     * @param showLoadingState A boolean indicating if the loading state should be shown (default is true).
     */
    protected fun <PARAMS, RESULT, UC : FudgeTvUseCaseWithParams<PARAMS, RESULT>> executeUseCaseWithParams(
        useCase: UC,
        params: PARAMS,
        onSuccess: (RESULT) -> Unit = {},
        onFailed: () -> Unit = {},
        onMapExceptionToState: ((Exception, STATE) -> STATE)? = null,
        showLoadingState: Boolean = true
    ) {
        if (showLoadingState) {
            onLoading()
        }
        useCase.invoke(
            scope = viewModelScope,
            params = params,
            onSuccess = {
                if (showLoadingState) {
                    onIdle()
                }
                onSuccess(it)
            },
            onError = {
                onErrorOccurred(it, onMapExceptionToState)
                onFailed()
            }
        )
    }

    /**
     * Updates the state to represent loading state.
     */
    private fun onLoading() {
        updateState {
            it.copyState(isLoading = true, errorMessage = null)
        }
    }

    /**
     * Updates the state to represent idle state.
     */
    private fun onIdle() {
        updateState {
            it.copyState(isLoading = false)
        }
    }

    /**
     * Handles the occurrence of an error.
     *
     * @param ex The exception that occurred.
     * @param onMapExceptionToState A function to map exceptions to the state.
     */
    private fun onErrorOccurred(
        ex: Exception,
        onMapExceptionToState: ((Exception, STATE) -> STATE)?
    ) {
        ex.printStackTrace()
        updateState {
            onMapExceptionToState?.invoke(ex, it.copyState(isLoading = false)) ?: run {
                it.copyState(
                    isLoading = false,
                    errorMessage = ex.message
                )
            }
        }
    }

    override fun onErrorMessageCleared() {
        updateState { it.copyState(errorMessage = null) }
    }
}

/**
 * Interface representing the UI state.
 */
abstract class UiState<out T : UiState<T>>(
    open val isLoading: Boolean,
    open val errorMessage: String?
) {
    abstract fun copyState(
        isLoading: Boolean = this.isLoading,
        errorMessage: String? = this.errorMessage
    ): T
}

/**
 * Interface representing side effects.
 */
interface SideEffect