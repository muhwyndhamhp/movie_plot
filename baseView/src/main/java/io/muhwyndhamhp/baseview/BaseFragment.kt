package io.muhwyndhamhp.baseview

import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment : Fragment() {
    val mainViewModel: MainViewModel by sharedViewModel()
}