package com.payclip.examplecleancode.arch

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class ScopedViewModel<UI : UiState, A: ActionState>(uiDispatcher: CoroutineDispatcher)
    : ViewModel(), Scope by Scope.Impl(uiDispatcher) {

    abstract val mutableState: MutableStateFlow<UI>
    val state: StateFlow<UI>
        get() = mutableState

    val actionFlow: MutableSharedFlow<A> = MutableSharedFlow()

    init {
        start()
    }

    private fun start() {
        initScope()
        viewModelScope.launch {
            actionFlow.collect(::onAction)
        }
    }

    abstract fun onAction(action: A)

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    fun renderOnView(state: UI) = launch {
        mutableState.emit(state)
    }

}
