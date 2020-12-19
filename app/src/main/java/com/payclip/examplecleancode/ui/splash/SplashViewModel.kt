package com.payclip.examplecleancode.ui.splash

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.payclip.domain.User
import com.payclip.examplecleancode.arch.ScopedViewModel
import com.payclip.examplecleancode.permissions.PermissionChecker
import com.payclip.usecases.GetUserUC
import com.payclip.usecases.RequestUserTokenUC
import com.payclip.usecases.SaveUserUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashViewModel(
    private val permissionChecker: PermissionChecker,
    private val googleAccountCredential: GoogleAccountCredential,
    getUserUC: GetUserUC,
    private val saveUser: SaveUserUC,
    private val requestToken: RequestUserTokenUC,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel<SplashUI, SplashAction>(uiDispatcher) {

    override val mutableState: MutableStateFlow<SplashUI> = MutableStateFlow(SplashUI.Init)

    private val userObserver = Observer<User?>(::handleUserResponse)
    private val userLiveData = getUserUC().asLiveData(Dispatchers.Main)

    val resultSessionCompletion: (Intent?) -> Unit = {
        it?.extras?.getString(AccountManager.KEY_ACCOUNT_NAME)?.let { account ->
            renderOnView(SplashUI.AccountSelected(account))
        } ?: renderOnView(SplashUI.AccountNotSelected)
    }

    val resultSessionTokenCompletion: (Intent?) -> Unit = {
        if (it != null) {
            renderOnView(SplashUI.NavigateToMain)
        } else {
            renderOnView(SplashUI.ShowError)
        }
    }

    private val permissionsNeeded = PermissionChecker.Permission.ACCOUNT

    init {
        initScope()
    }

    override fun onAction(action: SplashAction) {
        when (action) {
            SplashAction.Start -> renderOnView(SplashUI.Start)
            SplashAction.CheckPermissions -> checkPermissions()
            is SplashAction.RequestPermissions -> requestPermissions(action.activity)
            is SplashAction.CheckAccount -> checkAccount(action.lifecycle)
            is SplashAction.RequestAccount -> requestAccount(action.launcher)
            is SplashAction.SaveAccount -> saveUserAccount(action.account)
            is SplashAction.RequestYoutubePermissions -> action.launcher.launch(action.intent)
        }
    }

    private fun checkPermissions() {
        if (permissionChecker.check(permissionsNeeded)) {
            renderOnView(SplashUI.PermissionsGranted)
        } else {
            renderOnView(SplashUI.MissingPermissions)
        }
    }

    private fun requestPermissions(activity: Activity) = launch {
        if (permissionChecker.requestPermissions(activity, permissionsNeeded)) {
            renderOnView(SplashUI.PermissionsGranted)
        } else {
            renderOnView(SplashUI.PermissionsDenied)
        }
    }

    private fun checkAccount(lifecycle: LifecycleOwner) = launch {
        userLiveData.observe(lifecycle, userObserver)
    }

    private fun handleUserResponse(user: User?) {
        if (user != null) {
            userLiveData.removeObserver(userObserver)
            googleAccountCredential.selectedAccountName = user.accountName
            requestUserToken()
        } else {
            renderOnView(SplashUI.AccountNotExist)
        }
    }

    private fun requestUserToken() = launch {
        requestToken().collect {
            when {
                it.token != null -> {
                    renderOnView(SplashUI.NavigateToMain)
                }
                it.throwable is UserRecoverableAuthException -> {
                    val intent = (it.throwable as UserRecoverableAuthException).intent
                    renderOnView(SplashUI.YoutubePermissionsNeeded(intent))
                }
                else -> {
                    it.throwable?.printStackTrace()
                    renderOnView(SplashUI.ShowError)
                }
            }
        }
    }

    private fun requestAccount(activity: ActivityResultLauncher<Intent>) {
        activity.launch(googleAccountCredential.newChooseAccountIntent())
    }

    private fun saveUserAccount(account: String) = launch {
        if(!saveUser(User(accountName = account))) {
            renderOnView(SplashUI.ShowError)
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}
