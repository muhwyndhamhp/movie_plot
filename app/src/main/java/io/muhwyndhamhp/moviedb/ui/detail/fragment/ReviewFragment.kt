package io.muhwyndhamhp.moviedb.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.muhwyndhamhp.moviedb.base.BaseFragment
import io.muhwyndhamhp.moviedb.databinding.FragmentReviewBinding
import io.muhwyndhamhp.moviedb.viewmodel.ReviewViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ReviewFragment : BaseFragment() {
    lateinit var binding: FragmentReviewBinding

    private val reviewViewModel by lazy {
        requireParentFragment().getViewModel<ReviewViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewViewModel.reviews.observe(viewLifecycleOwner, {
            if(!it.isNullOrEmpty()){
                binding.isHasReview = true
                binding.rvReview.apply {
                    adapter = ReviewAdapter(it)
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            } else {
                binding.isHasReview = false
            }
        })
    }
}