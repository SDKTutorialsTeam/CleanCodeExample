package com.payclip.examplecleancode.ui.subscriptions

import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.usecases.GetSubscriptionsUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubscriptionsViewModel(
    private val getSubscriptions: GetSubscriptionsUC,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel<SubscriptionsUI, SubscriptionsAction>(uiDispatcher) {

    override val mutableState: MutableStateFlow<SubscriptionsUI> = MutableStateFlow(SubscriptionsUI.Init)

    override fun onAction(action: SubscriptionsAction) {
        when (action) {
            SubscriptionsAction.ObtainHomeChannels -> {
                obtainVideos()
            }
        }
    }

    private fun obtainVideos() = launch {
        renderOnView(SubscriptionsUI.Loading)
        getSubscriptions().collect {
            renderOnView(SubscriptionsUI.RenderChannels(it))
        }
    }

}
