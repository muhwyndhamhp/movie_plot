package io.muhwyndhamhp.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import io.muhwyndhamhp.moviedb.base.BaseFragment
import io.muhwyndhamhp.moviedb.databinding.FragmentHomeBinding
import io.muhwyndhamhp.moviedb.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    private val movieViewModel: MovieViewModel by viewModel()

    lateinit var popularAdapter: HomeAdapter
    lateinit var favouriteAdapter: HomeAdapter
    lateinit var upcomingAdapter: HomeAdapter

    private lateinit var navController: NavController
    private lateinit var pagerDecorator: PagerDecorator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        prepareObservers()
        navController = Navigation.findNavController(binding.root)
        movieViewModel.getPopular()
        movieViewModel.getUpcoming()
    }

    private fun prepareObservers() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner, {
            popularAdapter.addItem(it)
            if (::pagerDecorator.isInitialized) binding.rvPopular.removeItemDecoration(
                pagerDecorator
            )
            pagerDecorator = PagerDecorator(it.size, context)
            binding.rvPopular.addItemDecoration(pagerDecorator)
            mainViewModel.loading.postValue(false)
        })

        movieViewModel.upcomingMovies.observe(viewLifecycleOwner, {
            upcomingAdapter.addItem(it)
            mainViewModel.loading.postValue(false)
        })

        mainViewModel.userName.observe(viewLifecycleOwner, {
            binding.userName = it
        })

        movieViewModel.loading.observe(viewLifecycleOwner, {
            mainViewModel.loading.postValue(it)
        })

        movieViewModel.error.observe(viewLifecycleOwner, {
            mainViewModel.error.postValue(it)
        })
    }

    private fun prepareRecyclerView() {
        prepareAdapter()
        binding.rvPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        PagerSnapHelper().attachToRecyclerView(binding.rvPopular)

        binding.rvUpcoming.apply {
            adapter = upcomingAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }

    }

    private fun prepareAdapter() {
        popularAdapter = HomeAdapter(mutableListOf(), 0) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it))
        }

        upcomingAdapter = HomeAdapter(mutableListOf(), 1) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it))
        }
    }
}