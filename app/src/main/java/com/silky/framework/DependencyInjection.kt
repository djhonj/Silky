package com.silky.framework

import android.app.Application
import com.silky.data.interfaces.IRemoteVideoYT
import com.silky.data.repository.VideoYTRepository
import com.silky.framework.infraestructure.VideoYTRemote
import com.silky.framework.infraestructure.convert.Base64ToAudio
import com.silky.framework.ui.common.NotificationApp
import com.silky.framework.ui.main.MainActivity
import com.silky.framework.ui.main.MainPresenter
import com.silky.interactors.DownloadInteractor
import com.silky.interactors.SearchVideoInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDependencyInjection() {
    startKoin {
        androidContext(this@initDependencyInjection)
        modules(listOf(presenterModule, interactorModule, dataModule, infraestructureModule))
    }
}

//las dependencias que necesito en el modulo
val presenterModule = module {
    single { MainActivity() }
    single { (view: MainActivity) -> MainPresenter(view, get(), get()) }
    single { NotificationApp(androidContext()) }
}

val interactorModule = module {
    single { SearchVideoInteractor(get()) }
    single { DownloadInteractor(get()) }
}

val dataModule = module {
    single { VideoYTRepository(get()) }
}

val infraestructureModule = module {
    single<IRemoteVideoYT> { VideoYTRemote(get()) }
    single { Base64ToAudio(androidContext()) }
}