package com.payclip.examplecleancode.ui.popular

import android.os.Bundle
import android.view.View
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : BaseFragment<PopularUI, PopularViewModel>(R.layout.popular_fragment) {

    override val viewModel: PopularViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(PopularAction.ObtainPopularVideos)
    }

    override fun updateUi(state: UiState) {
        when(state) {
            PopularUI.Loading -> {}
            is PopularUI.RenderVideos -> {
                println("PopularFragment.updateUi --> ${state.videoList.toList()}")
            }
        }
    }

}