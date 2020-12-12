package com.payclip.examplecleancode

import android.app.Application
import com.google.android.gms.common.Scopes
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.youtube.YouTubeScopes
import com.payclip.data.repository.UserRepository
import com.payclip.data.repository.VideoRepository
import com.payclip.data.sources.LocalDataSource
import com.payclip.data.sources.RemoteDataSource
import com.payclip.examplecleancode.database.RoomDataSource
import com.payclip.examplecleancode.database.UserDataBase
import com.payclip.examplecleancode.permissions.AndroidPermissionChecker
import com.payclip.examplecleancode.permissions.PermissionChecker
import com.payclip.examplecleancode.server.YoutubeApi
import com.payclip.examplecleancode.server.YoutubeRemoteDataSource
import com.payclip.examplecleancode.ui.dashboard.DashBoardViewModel
import com.payclip.examplecleancode.ui.dashboard.DashboardFragment
import com.payclip.examplecleancode.ui.splash.SplashFragment
import com.payclip.examplecleancode.ui.splash.SplashViewModel
import com.payclip.usecases.GetUserUC
import com.payclip.usecases.SaveUserUC
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidContext(this@initDI)
        androidLogger()
        modules(listOf(google, youtube, appModule, dataModule, scopesModule))
    }
}

private val google = module {
    single {
        GoogleAccountCredential.usingOAuth2(
            androidContext(), listOf(
                Scopes.PROFILE, YouTubeScopes.YOUTUBE
            )
        ).setBackOff(ExponentialBackOff())
    }
}

private val youtube = module {
    single(named("appName")) { androidApplication().getString(R.string.app_name) }
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    factory {
        YoutubeApi(get(named("appName")), get())
    }
}

private val appModule = module {
    factory<PermissionChecker> { AndroidPermissionChecker(androidContext()) }
    single { UserDataBase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { YoutubeRemoteDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory { UserRepository(get()) }
    factory { VideoRepository(get(), get(named("apiKey"))) }
}

private val scopesModule = module {
    scope(named<SplashFragment>()) {
        viewModel { SplashViewModel(get(), get(), get(), get(), get()) }
        scoped { SaveUserUC(get()) }
        scoped { GetUserUC(get()) }
    }
    scope(named<DashboardFragment>()) {
        viewModel { DashBoardViewModel(get()) }
    }
}
