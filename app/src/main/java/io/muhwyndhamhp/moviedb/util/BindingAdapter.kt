package io.muhwyndhamhp.moviedb.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import io.muhwyndhamhp.moviedb.BuildConfig

object BindingAdapter {

    @BindingAdapter("app:imageUrl")
    fun setImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(BuildConfig.TMDB_ASSET_BASE_URL + url).into(view)
    }
}