package io.muhwyndhamhp.basemodule.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.muhwyndhamhp.basemodule.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM table_favourite_movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Insert
    fun insertMovie(movie : Movie?)

    @Delete
    fun delete(movie: Movie?)
}