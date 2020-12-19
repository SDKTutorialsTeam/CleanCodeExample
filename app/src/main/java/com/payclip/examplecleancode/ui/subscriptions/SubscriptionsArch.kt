package com.payclip.examplecleancode.ui.subscriptions

import com.payclip.domain.Channel
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class SubscriptionsAction : ActionState {
    object ObtainHomeChannels : SubscriptionsAction()
}

sealed class SubscriptionsUI : UiState {
    object Init: SubscriptionsUI()
    object Loading: SubscriptionsUI()
    data class RenderChannels(val channelsList: List<Channel>): SubscriptionsUI()
}
