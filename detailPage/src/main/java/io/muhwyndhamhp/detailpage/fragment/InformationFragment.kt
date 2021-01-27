package io.muhwyndhamhp.detailpage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.muhwyndhamhp.detailpage.databinding.FragmentInformationBinding
import io.muhwyndhamhp.moviedb.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel


class InformationFragment : io.muhwyndhamhp.baseview.BaseFragment() {

    lateinit var binding: FragmentInformationBinding

    private val movieViewModel by lazy {
        requireParentFragment().getViewModel<MovieViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.currentMovie.observe(viewLifecycleOwner, {
            binding.movie = it
            mainViewModel.setLoadingState(false)
        })
    }
}