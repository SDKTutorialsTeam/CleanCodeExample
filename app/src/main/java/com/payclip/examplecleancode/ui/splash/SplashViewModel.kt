package com.payclip.examplecleancode.ui.splash

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.payclip.data.models.Result
import com.payclip.domain.User
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.examplecleancode.permissions.PermissionChecker
import com.payclip.usecases.ExistUserUC
import com.payclip.usecases.SaveUserUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class SplashViewModel(
    private val permissionChecker: PermissionChecker,
    private val googleAccountCredential: GoogleAccountCredential,
    private val existUser: ExistUserUC,
    private val saveUser: SaveUserUC,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel<SplashUI>(uiDispatcher) {

    override val model: LiveData<SplashUI>
        get() {
            if (mModel.value == null) dispatch(SplashAction.Start)
            return mModel
        }

    val resultSessionCompletion: (Intent?) -> Unit = {
        it?.extras?.getString(AccountManager.KEY_ACCOUNT_NAME)?.let { account ->
            consume(SplashUI.AccountSelected(account))
        } ?: consume(SplashUI.AccountNotSelected)
    }

    private val permissionsNeeded = PermissionChecker.Permission.ACCOUNT

    init {
        initScope()
    }

    override fun dispatch(action: ActionState) {
        when (action) {
            SplashAction.Start -> consume(SplashUI.Start)
            SplashAction.CheckPermissions -> checkPermissions()
            is SplashAction.RequestPermissions -> requestPermissions(action.activity)
            SplashAction.CheckAccount -> checkAccount()
            is SplashAction.RequestAccount -> requestAccount(action.launcher)
            is SplashAction.SaveAccount -> saveUserAccount(action.account)
        }
    }

    private fun checkPermissions() {
        if (permissionChecker.check(permissionsNeeded)) {
            consume(SplashUI.PermissionsGranted)
        } else {
            consume(SplashUI.MissingPermissions)
        }
    }

    private fun requestPermissions(activity: Activity) = launch {
        if (permissionChecker.requestPermissions(activity, permissionsNeeded)) {
            consume(SplashUI.PermissionsGranted)
        } else {
            consume(SplashUI.PermissionsDenied)
        }
    }

    private fun checkAccount() = launch {
        if (existUser()) {
            consume(SplashUI.NavigateToMain)
        } else {
            consume(SplashUI.AccountNotExist)
        }
    }

    private fun requestAccount(activity: ActivityResultLauncher<Intent>) {
        activity.launch(googleAccountCredential.newChooseAccountIntent())
    }

    private fun saveUserAccount(account: String) = launch {
        when(saveUser(User(accountName = account))) {
            is Result.Success -> consume(SplashUI.NavigateToMain)
            is Result.Failure -> consume(SplashUI.ShowError)
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}
