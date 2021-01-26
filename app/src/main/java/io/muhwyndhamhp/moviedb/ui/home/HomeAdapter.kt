package io.muhwyndhamhp.moviedb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.muhwyndhamhp.moviedb.R
import io.muhwyndhamhp.moviedb.data.model.Movie
import io.muhwyndhamhp.moviedb.databinding.ItemPopularMovieBinding
import io.muhwyndhamhp.moviedb.databinding.ItemUpcomingBinding

class HomeAdapter(
    private var movieList: MutableList<Movie>,
    private val itemViewType: Int,
    private val clickListener: (Movie) -> Unit
) : RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindItem(movie: Movie, position: Int, clickListener: (Movie) -> Unit)
    }

    class PopularViewHolder(private val itemPopularMovieBinding: ItemPopularMovieBinding) :
        BaseViewHolder(itemPopularMovieBinding.root) {
        override fun bindItem(movie: Movie, position: Int, clickListener: (Movie) -> Unit) {
            itemPopularMovieBinding.movie = movie
            itemPopularMovieBinding.root.setOnClickListener { clickListener.invoke(movie) }
        }
    }

    class UpcomingViewHolder(private val itemUpcomingBinding: ItemUpcomingBinding) :
        BaseViewHolder(itemUpcomingBinding.root) {
        override fun bindItem(movie: Movie, position: Int, clickListener: (Movie) -> Unit) {
            itemUpcomingBinding.movie = movie
            itemUpcomingBinding.root.setOnClickListener {
                clickListener.invoke(movie)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            0 -> PopularViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_popular_movie,
                    parent,
                    false
                )
            )
            1 -> UpcomingViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_upcoming,
                    parent,
                    false
                )
            )
            else -> PopularViewHolder(
                DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_popular_movie,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindItem(movieList[position], position, clickListener)
    }

    override fun getItemCount() = movieList.size

    override fun getItemViewType(position: Int): Int {
        return itemViewType
    }

    fun addItem(addedMovies: List<Movie>){
        val a = movieList.size
        movieList.addAll(addedMovies)
        notifyItemRangeInserted(a, movieList.size - 1)
    }

    fun updateItem(updatedList: List<Movie>) {
        movieList = updatedList as MutableList<Movie>
        notifyDataSetChanged()
    }
}