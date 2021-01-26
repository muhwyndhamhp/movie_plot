package io.muhwyndhamhp.moviedb.base

import androidx.fragment.app.Fragment
import io.muhwyndhamhp.moviedb.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment : Fragment() {
    val mainViewModel: MainViewModel by sharedViewModel()
}