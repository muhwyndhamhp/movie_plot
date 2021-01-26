package io.muhwyndhamhp.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import io.muhwyndhamhp.moviedb.R
import io.muhwyndhamhp.moviedb.base.BaseFragment
import io.muhwyndhamhp.moviedb.databinding.FragmentHomeBinding
import io.muhwyndhamhp.moviedb.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding

    val movieViewModel: MovieViewModel by viewModel()

    lateinit var popularAdapter: HomeAdapter

    lateinit var pagerDecorator: PagerDecorator

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
        movieViewModel.getPopular()
    }

    private fun prepareObservers() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner, {
            popularAdapter.addItem(it)
            if (::pagerDecorator.isInitialized) binding.rvPopular.removeItemDecoration(
                pagerDecorator
            )
            pagerDecorator = PagerDecorator(it.size, context)
            binding.rvPopular.addItemDecoration(pagerDecorator)
        })
    }

    private fun prepareRecyclerView() {
        prepareAdapter()
        binding.rvPopular.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        PagerSnapHelper().attachToRecyclerView(binding.rvPopular)
    }

    private fun prepareAdapter() {
        popularAdapter = HomeAdapter(mutableListOf(), 0) {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_movieDetailFragment)
        }
    }
}