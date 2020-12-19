package com.payclip.examplecleancode.ui.splash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.extensions.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashUI, SplashAction, SplashViewModel>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentResultCompletion = viewModel.resultSessionCompletion
        sendAction(SplashAction.Start)
    }

    override fun render(state: SplashUI) {
        when (state) {
            SplashUI.Init -> { }
            SplashUI.Start -> sendAction(SplashAction.CheckPermissions)
            SplashUI.MissingPermissions -> {
                activity?.let {
                    sendAction(SplashAction.RequestPermissions(it))
                }
            }
            SplashUI.PermissionsDenied -> Toast.makeText(requireContext(), "Permisos necesarios para continuar...", Toast.LENGTH_SHORT).show()
            SplashUI.PermissionsGranted -> sendAction(SplashAction.CheckAccount(viewLifecycleOwner))
            SplashUI.AccountNotExist -> sendAction(SplashAction.RequestAccount(registerForResult))
            SplashUI.AccountNotSelected -> Toast.makeText(requireContext(), "Cuenta necesaria para continuar...", Toast.LENGTH_SHORT).show()
            is SplashUI.AccountSelected -> sendAction(SplashAction.SaveAccount(state.accountName))
            is SplashUI.YoutubePermissionsNeeded -> {
                fragmentResultCompletion = viewModel.resultSessionTokenCompletion
                sendAction(SplashAction.RequestYoutubePermissions(registerForResult, state.intent))
            }
            SplashUI.NavigateToMain -> navigateTo(R.id.action_splashFragment_to_dashboardFragment)
            SplashUI.ShowError -> Toast.makeText(requireContext(), "Ocurrió un error al guardar los datos...", Toast.LENGTH_SHORT).show()
        }
    }

}
