package com.payclip.examplecleancode.arch

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher

abstract class ScopedViewModel<UI : UiState>(uiDispatcher: CoroutineDispatcher)
    : ViewModel(), Scope by Scope.Impl(uiDispatcher) {

    val mModel = MutableLiveData<UI>()
    abstract val model: LiveData<UI>

    init {
        initScope()
    }

    abstract fun dispatch(action: ActionState)

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    fun consume(state: UI) {
        mModel.value = state
    }
}
