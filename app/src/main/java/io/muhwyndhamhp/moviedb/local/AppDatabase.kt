package io.muhwyndhamhp.moviedb.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.muhwyndhamhp.moviedb.data.model.Movie
import io.muhwyndhamhp.moviedb.data.model.Review
import io.muhwyndhamhp.moviedb.local.dao.MovieDao
import io.muhwyndhamhp.moviedb.util.TypeConverter

@Database(entities = [Movie::class, Review::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}