package io.muhwyndhamhp.moviedb.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.muhwyndhamhp.moviedb.BuildConfig
import io.muhwyndhamhp.moviedb.R

object BindingAdapter {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setTMDBImage(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(if (url != null) BuildConfig.TMDB_ASSET_BASE_URL + url else R.drawable.ic_banner_foreground)
            .into(view)
    }

    @BindingAdapter("app:setFavouriteIcon")
    @JvmStatic
    fun setFavouriteIcon(view: FloatingActionButton, isFavourite: Boolean) {
        view.setImageResource(if (isFavourite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
    }
}