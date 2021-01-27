package io.muhwyndhamhp.onboardingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.muhwyndhamhp.moviedb.R
import io.muhwyndhamhp.moviedb.util.Extension.assertNickName
import io.muhwyndhamhp.moviedb.util.Extension.onReturnKey
import io.muhwyndhamhp.onboardingpage.databinding.FragmentOnboardingBinding


class OnboardingFragment : io.muhwyndhamhp.baseview.BaseFragment() {

    lateinit var binding: FragmentOnboardingBinding
    var isInputValid = false
    var finalizedNickname = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachEditTextObserver()
        binding.layoutInteractiveButton.btContinue.setOnClickListener {
            mainViewModel.savableUsername.postValue(finalizedNickname)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_onboardingFragment_to_homeFragment)
        }
    }

    private fun attachEditTextObserver() {
        binding.layoutInteractiveInput.etNickname.assertNickName { isValid, reason ->
            if (!isValid) {
                binding.errorMessage = reason
                binding.nickName = ""
                updateNickname("")
            } else {
                binding.nickName = binding.layoutInteractiveInput.etNickname.text.toString()
                binding.errorMessage = ""
            }
            isInputValid = isValid
        }

        binding.layoutInteractiveInput.etNickname.onReturnKey {
            if (isInputValid) {
                updateNickname(binding.layoutInteractiveInput.etNickname.text.toString())
            }
        }
    }

    private fun updateNickname(nickName: String) {
        finalizedNickname = nickName
        binding.validatedNickname = finalizedNickname
    }
}