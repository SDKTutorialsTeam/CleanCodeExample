package com.payclip.examplecleancode.ui.dashboard

import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class DashboardAction : ActionState {
    object Start : DashboardAction()
}

sealed class DashboardUI : UiState {
    object ChangeNavigation: DashboardUI()
}