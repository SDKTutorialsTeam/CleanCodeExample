package com.payclip.examplecleancode.ui.popular

import androidx.lifecycle.LiveData
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.usecases.GetPopularVideosUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PopularViewModel(private val getPopularVideos: GetPopularVideosUC, uiDispatcher: CoroutineDispatcher) : ScopedViewModel<PopularUI>(uiDispatcher) {

    override val model: LiveData<PopularUI>
        get() = mModel

    override fun dispatch(action: ActionState) {
        when(action) {
            PopularAction.ObtainPopularVideos -> {
                obtainVideos()
            }
        }
    }

    private fun obtainVideos() = launch {
        consume(PopularUI.Loading)
        getPopularVideos().collect {
            consume(PopularUI.RenderVideos(it))
        }
    }

}
