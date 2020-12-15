package com.payclip.examplecleancode.ui.home

import androidx.lifecycle.LiveData
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.usecases.GetHomeVideosUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val getHomeVideos: GetHomeVideosUC, uiDispatcher: CoroutineDispatcher) : ScopedViewModel<HomeUI>(uiDispatcher) {

    override val model: LiveData<HomeUI>
        get() = mModel

    override fun dispatch(action: ActionState) {
        when(action) {
            HomeAction.ObtainHomeVideos -> {
                obtainVideos()
            }
        }
    }

    private fun obtainVideos() = launch {
        consume(HomeUI.Loading)
        getHomeVideos().collect {
            consume(HomeUI.RenderVideos(it))
        }
    }

}
