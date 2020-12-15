package com.payclip.examplecleancode.ui.dashboard

import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<DashboardUI, DashBoardViewModel>(R.layout.dashboard_fragment) {

    override val viewModel: DashBoardViewModel by viewModel()

    override fun updateUi(state: UiState) {

    }


}
