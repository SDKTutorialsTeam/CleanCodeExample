package com.payclip.examplecleancode.ui.popular

import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.usecases.GetPopularVideosUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PopularViewModel(private val getPopularVideos: GetPopularVideosUC, uiDispatcher: CoroutineDispatcher) : ScopedViewModel<PopularUI, PopularAction>(uiDispatcher) {

    override val mutableState: MutableStateFlow<PopularUI> = MutableStateFlow(PopularUI.Init)

    override fun onAction(action: PopularAction) {
        when(action) {
            PopularAction.ObtainPopularVideos -> {
                obtainVideos()
            }
        }
    }

    private fun obtainVideos() = launch {
        renderOnView(PopularUI.Loading)
        getPopularVideos().collect {
            renderOnView(PopularUI.RenderVideos(it))
        }
    }

}
