package com.payclip.examplecleancode.ui.subscriptions

import androidx.lifecycle.LiveData
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.usecases.GetSubscriptionsUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubscriptionsViewModel(private val getSubscriptions: GetSubscriptionsUC, uiDispatcher: CoroutineDispatcher) : ScopedViewModel<SubscriptionsUI>(uiDispatcher) {

    override val model: LiveData<SubscriptionsUI>
        get() = mModel

    override fun dispatch(action: ActionState) {
        when(action) {
            SubscriptionsAction.ObtainHomeChannels -> {
                obtainVideos()
            }
        }
    }

    private fun obtainVideos() = launch {
        consume(SubscriptionsUI.Loading)
        getSubscriptions().collect {
            consume(SubscriptionsUI.RenderChannels(it))
        }
    }

}
