package com.payclip.examplecleancode.ui.splash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import com.payclip.examplecleancode.extensions.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashUI, SplashViewModel>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            fragmentResultCompletion = resultSessionCompletion
        }
    }

    override fun updateUi(state: UiState) {
        when (state) {
            SplashUI.Start -> viewModel.dispatch(SplashAction.CheckPermissions)
            SplashUI.MissingPermissions -> {
                activity?.let {
                    viewModel.dispatch(SplashAction.RequestPermissions(it))
                }
            }
            SplashUI.PermissionsDenied -> Toast.makeText(requireContext(), "Permisos necesarios para continuar...", Toast.LENGTH_SHORT).show()
            SplashUI.PermissionsGranted -> viewModel.dispatch(SplashAction.CheckAccount)
            SplashUI.AccountNotExist -> viewModel.dispatch(SplashAction.RequestAccount(registerForResult))
            SplashUI.AccountNotSelected -> Toast.makeText(requireContext(), "Cuenta necesaria para continuar...", Toast.LENGTH_SHORT).show()
            is SplashUI.AccountSelected -> viewModel.dispatch(SplashAction.SaveAccount(state.accountName))
            SplashUI.NavigateToMain -> navigateTo(R.id.action_splashFragment_to_dashboardFragment)
            SplashUI.ShowError -> Toast.makeText(requireContext(), "Ocurrió un error al guardar los datos...", Toast.LENGTH_SHORT).show()
        }
    }

}
