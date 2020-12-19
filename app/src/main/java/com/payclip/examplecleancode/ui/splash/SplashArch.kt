package com.payclip.examplecleancode.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LifecycleOwner
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class SplashAction : ActionState {
    object Start : SplashAction()
    object CheckPermissions : SplashAction()
    data class RequestPermissions(val activity: Activity) : SplashAction()
    data class CheckAccount(val lifecycle: LifecycleOwner) : SplashAction()
    data class RequestAccount(val launcher: ActivityResultLauncher<Intent>) : SplashAction()
    data class SaveAccount(val account: String) : SplashAction()
    data class RequestYoutubePermissions(val launcher: ActivityResultLauncher<Intent>, val intent: Intent): SplashAction()
}

sealed class SplashUI : UiState {
    object Init : SplashUI()
    object Start : SplashUI()
    object MissingPermissions : SplashUI()
    object PermissionsDenied : SplashUI()
    object PermissionsGranted : SplashUI()
    object AccountNotExist : SplashUI()
    object AccountNotSelected : SplashUI()
    data class AccountSelected(val accountName: String) : SplashUI()
    data class YoutubePermissionsNeeded(val intent: Intent): SplashUI()
    object NavigateToMain : SplashUI()
    object ShowError : SplashUI()
}
