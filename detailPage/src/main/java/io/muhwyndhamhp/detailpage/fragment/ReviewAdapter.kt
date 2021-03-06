package io.muhwyndhamhp.detailpage.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.muhwyndhamhp.basemodule.data.model.Review
import io.muhwyndhamhp.detailpage.R
import io.muhwyndhamhp.detailpage.databinding.ItemReviewBinding

class ReviewAdapter(private val reviewList: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    class ViewHolder(private val itemReviewBinding: ItemReviewBinding) :
        RecyclerView.ViewHolder(itemReviewBinding.root) {
        fun bindItem(review: Review) {
            itemReviewBinding.review = review
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_review,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(reviewList[position])
    }

    override fun getItemCount() = reviewList.size
}