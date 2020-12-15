package com.payclip.examplecleancode.ui.home

import android.os.Bundle
import android.view.View
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeUI, HomeViewModel>(R.layout.home_fragment) {

    override val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(HomeAction.ObtainHomeVideos)
    }

    override fun updateUi(state: UiState) {
        when(state) {
            HomeUI.Loading -> {}
            is HomeUI.RenderVideos -> {
                println("HomeFragment.updateUi --> ${state.videoList.toList()}")
            }
        }
    }

}