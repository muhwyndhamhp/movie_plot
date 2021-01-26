package io.muhwyndhamhp.moviedb.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.muhwyndhamhp.moviedb.R
import io.muhwyndhamhp.moviedb.base.BaseFragment
import io.muhwyndhamhp.moviedb.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            val navController = Navigation.findNavController(binding.root)
            mainViewModel.userName.observe(viewLifecycleOwner, {
                if (it != null) navController.navigate(R.id.action_splashFragment_to_homeFragment)
                else navController.navigate(R.id.action_splashFragment_to_onboardingFragment)
            })
        }, 2000)
    }
}