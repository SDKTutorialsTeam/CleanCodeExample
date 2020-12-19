package com.payclip.examplecleancode.ui.search

import com.payclip.examplecleancode.arch.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow

class SearchViewModel(uiDispatcher: CoroutineDispatcher) : ScopedViewModel<SearchUI, SearchAction>(uiDispatcher) {

    override val mutableState: MutableStateFlow<SearchUI> = MutableStateFlow(SearchUI.Init)

    override fun onAction(action: SearchAction) {

    }

}
