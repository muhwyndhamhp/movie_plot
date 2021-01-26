package io.muhwyndhamhp.moviedb.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import io.muhwyndhamhp.moviedb.BuildConfig
import io.muhwyndhamhp.moviedb.R

object BindingAdapter {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setTMDBImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(if(url != null) BuildConfig.TMDB_ASSET_BASE_URL + url else R.drawable.ic_banner_foreground).into(view)
    }
}