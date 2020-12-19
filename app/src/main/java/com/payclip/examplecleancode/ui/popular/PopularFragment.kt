package com.payclip.examplecleancode.ui.popular

import android.os.Bundle
import android.view.View
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : BaseFragment<PopularUI, PopularAction, PopularViewModel>(R.layout.popular_fragment) {

    override val viewModel: PopularViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendAction(PopularAction.ObtainPopularVideos)
    }

    override fun render(state: PopularUI) {
        when(state) {
            PopularUI.Init -> {}
            PopularUI.Loading -> {}
            is PopularUI.RenderVideos -> {
                println("PopularFragment.updateUi --> ${state.videoList.toList()}")
            }
        }
    }

}