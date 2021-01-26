package io.muhwyndhamhp.moviedb.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "table_favourite_movies")
data class Movie (
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var title: String? = "",
    var poster_path: String? = "",
    var adult: Boolean? = false,
    var overview: String? = "",
    var release_date: String? = "",
    var genre_ids: List<Int>? = listOf(),
    var original_title: String? = "",
    var original_language: String? = "",
    var backdrop_path: String? = "",
    var popularity: Double? = 0.0,
    var vote_count: Int? = 0,
    var vote_average: Double? = 0.0,
    var video: Boolean? = false
): Parcelable