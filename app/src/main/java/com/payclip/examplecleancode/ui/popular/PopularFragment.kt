package com.payclip.examplecleancode.ui.popular

import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularFragment : BaseFragment<PopularUI, PopularViewModel>(R.layout.popular_fragment) {

    override val viewModel: PopularViewModel by viewModel()

    override fun updateUi(state: UiState) {

    }

}