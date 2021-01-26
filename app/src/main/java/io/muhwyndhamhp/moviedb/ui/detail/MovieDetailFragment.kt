package io.muhwyndhamhp.moviedb.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import io.muhwyndhamhp.moviedb.base.BaseFragment
import io.muhwyndhamhp.moviedb.databinding.FragmentMovieDetailBinding
import io.muhwyndhamhp.moviedb.viewmodel.MovieViewModel
import io.muhwyndhamhp.moviedb.viewmodel.ReviewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailFragment : BaseFragment() {

    lateinit var binding: FragmentMovieDetailBinding

    private val reviewViewModel: ReviewViewModel by viewModel()
    private val movieViewModel: MovieViewModel by viewModel()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = DetailStateAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Information"
                else -> "Comment"
            }
        }.attach()

        attachObservers()
        movieViewModel.getDetails(args.movie)
        reviewViewModel.getReviews(args.movie.id)

    }

    private fun attachObservers() {
        movieViewModel.loading.observe(viewLifecycleOwner, { mainViewModel.loading.postValue(it) })
        reviewViewModel.loading.observe(viewLifecycleOwner, { mainViewModel.loading.postValue(it) })
        movieViewModel.error.observe(viewLifecycleOwner, { mainViewModel.error.postValue(it) })
        reviewViewModel.error.observe(viewLifecycleOwner, { mainViewModel.error.postValue(it) })
    }
}