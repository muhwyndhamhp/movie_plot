package io.muhwyndhamhp.moviedb.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { ReviewViewModel(get()) }
        viewModel { io.muhwyndhamhp.baseview.MainViewModel() }
    }
}