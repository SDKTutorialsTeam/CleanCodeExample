package com.payclip.examplecleancode.ui.subscriptions

import android.os.Bundle
import android.view.View
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubscriptionsFragment : BaseFragment<SubscriptionsUI, SubscriptionsViewModel>(R.layout.subscriptions_fragment) {

    override val viewModel: SubscriptionsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(SubscriptionsAction.ObtainHomeChannels)
    }

    override fun updateUi(state: UiState) {
        when(state) {
            SubscriptionsUI.Loading -> {}
            is SubscriptionsUI.RenderChannels -> {
                println("SubscriptionsFragment.updateUi --> ${state.channelsList.toList()}")
            }
        }
    }

}