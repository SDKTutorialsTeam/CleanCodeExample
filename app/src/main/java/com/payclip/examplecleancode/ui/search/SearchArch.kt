package com.payclip.examplecleancode.ui.search

import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class SearchAction : ActionState {
    object Start : SearchAction()
}

sealed class SearchUI : UiState {
    object ChangeNavigation: SearchUI()
}
