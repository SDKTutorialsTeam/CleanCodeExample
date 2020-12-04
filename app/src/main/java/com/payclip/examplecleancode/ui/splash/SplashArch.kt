package com.payclip.examplecleancode.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class SplashAction : ActionState {
    object Start : SplashAction()
    object CheckPermissions : SplashAction()
    data class RequestPermissions(val activity: Activity) : SplashAction()
    object CheckAccount : SplashAction()
    data class RequestAccount(val launcher: ActivityResultLauncher<Intent>) : SplashAction()
    data class SaveAccount(val account: String) : SplashAction()
}

sealed class SplashUI : UiState {
    object Start : SplashUI()
    object MissingPermissions : SplashUI()
    object PermissionsDenied : SplashUI()
    object PermissionsGranted : SplashUI()
    object AccountNotExist : SplashUI()
    object AccountNotSelected : SplashUI()
    data class AccountSelected(val accountName: String) : SplashUI()
    object NavigateToMain : SplashUI()
    object ShowError : SplashUI()
}
