package com.payclip.examplecleancode.ui.dashboard

import androidx.lifecycle.LiveData
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher

class DashBoardViewModel(uiDispatcher: CoroutineDispatcher) : ScopedViewModel<DashboardUI>(uiDispatcher) {
    override val model: LiveData<DashboardUI>
        get() {
            if (mModel.value == null) dispatch(DashboardAction.Start)
            return mModel
        }

    override fun dispatch(action: ActionState) {

    }

}