package io.muhwyndhamhp.moviedb.base

import android.app.Application
import io.muhwyndhamhp.moviedb.data.AppRepository
import io.muhwyndhamhp.moviedb.local.LocalModule
import io.muhwyndhamhp.moviedb.network.NetworkModule
import io.muhwyndhamhp.moviedb.viewmodel.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MovieDBApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieDBApp)
            modules(listOf(
                NetworkModule.networkModule,
                LocalModule.localModule,
                ViewModelModule.viewModelModule,
                module {
                    single { AppRepository(get(), get()) }
                }
            ))
        }
    }
}