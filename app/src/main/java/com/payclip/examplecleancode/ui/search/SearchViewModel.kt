package com.payclip.examplecleancode.ui.search

import androidx.lifecycle.LiveData
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher

class SearchViewModel(uiDispatcher: CoroutineDispatcher) : ScopedViewModel<SearchUI>(uiDispatcher) {

    override val model: LiveData<SearchUI>
        get() = mModel

    override fun dispatch(action: ActionState) {

    }

}
