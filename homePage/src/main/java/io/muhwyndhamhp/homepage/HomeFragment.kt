package io.muhwyndhamhp.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import io.muhwyndhamhp.homepage.databinding.FragmentHomeBinding
import io.muhwyndhamhp.moviedb.util.Constants.TICKER_REPEAT
import io.muhwyndhamhp.moviedb.util.Extension.updateVisibleItem
import io.muhwyndhamhp.moviedb.viewmodel.MovieViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : io.muhwyndhamhp.baseview.BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    private val movieViewModel: MovieViewModel by viewModel()

    lateinit var popularAdapter: HomeAdapter
    lateinit var favouriteAdapter: HomeAdapter
    lateinit var upcomingAdapter: HomeAdapter

    private lateinit var navController: NavController
    private lateinit var pagerDecorator: PagerDecorator
    private var carouselPosition = 0
    private var timer: Timer? = Timer()
    private var ticker = ticker(3000, 0)

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

    override fun onPause() {
        super.onPause()
        resetTicker()
    }

    private fun prepareObservers() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner, {
            popularAdapter.updateItem(it)
            if (::pagerDecorator.isInitialized) binding.rvPopular.removeItemDecoration(
                pagerDecorator
            )
            pagerDecorator = PagerDecorator(it.size, context)
            binding.rvPopular.addItemDecoration(pagerDecorator)
            mainViewModel.setLoadingState(false)
            attachAutoScrollScheduler(it.size)
        })

        movieViewModel.favouriteMovies.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                binding.hasFavourite = true
                favouriteAdapter.updateItem(it)
            } else {
                binding.hasFavourite = false
            }
        })

        movieViewModel.upcomingMovies.observe(viewLifecycleOwner, {
            upcomingAdapter.updateItem(it)
            mainViewModel.setLoadingState(false)
        })

        mainViewModel.userName.observe(viewLifecycleOwner, {
            binding.userName = it
        })

        movieViewModel.loading.observe(viewLifecycleOwner, {
            mainViewModel.setLoadingState(it)
        })

        movieViewModel.error.observe(viewLifecycleOwner, {
            mainViewModel.setErrorState(it)
        })
    }

    private fun prepareRecyclerView() {
        prepareAdapter()
        binding.rvPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            updateVisibleItem { carouselPosition = it }
        }
        PagerSnapHelper().attachToRecyclerView(binding.rvPopular)

        binding.rvFavourite.apply {
            adapter = favouriteAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvUpcoming.apply {
            adapter = upcomingAdapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        }

    }

    private fun attachAutoScrollScheduler(itemSize: Int) {
       resetTicker(true)
        GlobalScope.launch {
            repeat(TICKER_REPEAT) {
                ticker.receive()
                if (carouselPosition >= itemSize - 1) carouselPosition = -1
                binding.rvPopular.smoothScrollToPosition(++carouselPosition)
            }
        }
    }


    private fun resetTicker(reattach: Boolean = false) {
        ticker.cancel()
        if(reattach) ticker = ticker(3000, 0)
    }

    private fun prepareAdapter() {
        popularAdapter = HomeAdapter(mutableListOf(), 0) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it))
        }

        favouriteAdapter = HomeAdapter(mutableListOf(), 2) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it))
        }

        upcomingAdapter = HomeAdapter(mutableListOf(), 1) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it))
        }
    }
}