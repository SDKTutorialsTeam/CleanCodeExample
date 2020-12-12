package com.payclip.examplecleancode.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.payclip.domain.User
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.examplecleancode.extensions.collectOnMain
import com.payclip.usecases.SearchVideoUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DashBoardViewModel(private val searchVideos: SearchVideoUC, uiDispatcher: CoroutineDispatcher) : ScopedViewModel<DashboardUI>(uiDispatcher) {

    override val model: LiveData<DashboardUI>
        get() {
            if (mModel.value == null) dispatch(DashboardAction.Start)
            return mModel
        }

    override fun dispatch(action: ActionState) {
        when(action) {
            DashboardAction.Start -> {
                searchVideos("pow patrol").collectOnMain {
                    println("DashBoardViewModel.dispatch --> $it")
                }
            }
        }
    }

}