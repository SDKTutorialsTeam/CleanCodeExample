package com.payclip.examplecleancode.ui.popular

import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class PopularAction : ActionState {
    object Start : PopularAction()
}

sealed class PopularUI : UiState {
    object ChangeNavigation: PopularUI()
}
