package io.muhwyndhamhp.basemodule.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "table_favourite_reviews")
data class Review(
    var author: String? = "",
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var content: String? = "",
    var created_at: String? = "",
    var updated_at: String? = "",
    var url: String? = "",
    var movieId: Int? = 0
) : Parcelable