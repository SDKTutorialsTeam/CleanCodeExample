package com.payclip.examplecleancode.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.databinding.DashboardFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<DashboardUI, DashboardAction, DashBoardViewModel>(R.layout.dashboard_fragment) {

    override val viewModel: DashBoardViewModel by viewModel()
    private lateinit var binding: DashboardFragmentBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DashboardFragmentBinding.bind(view)

        sendAction(DashboardAction.Start)
    }

    override fun render(state: DashboardUI) {
        when(state) {
            DashboardUI.Init -> {}
            DashboardUI.ChangeNavigation -> {
                navController = (childFragmentManager.findFragmentById(R.id.fragmentDashboard) as NavHostFragment).navController
                binding.dashboardBottomNav.setupWithNavController(navController)
            }
        }
    }

}
