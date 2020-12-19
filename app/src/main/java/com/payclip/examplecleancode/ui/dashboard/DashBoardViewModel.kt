package com.payclip.examplecleancode.ui.dashboard

import com.payclip.examplecleancode.arch.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow

class DashBoardViewModel(uiDispatcher: CoroutineDispatcher) : ScopedViewModel<DashboardUI, DashboardAction>(uiDispatcher) {

    override val mutableState: MutableStateFlow<DashboardUI> = MutableStateFlow(DashboardUI.Init)

    override fun onAction(action: DashboardAction) {
        when(action) {
            DashboardAction.Start -> {
                renderOnView(DashboardUI.ChangeNavigation)
            }
        }
    }

}
