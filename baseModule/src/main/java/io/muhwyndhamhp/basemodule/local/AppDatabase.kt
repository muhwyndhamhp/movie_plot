package io.muhwyndhamhp.basemodule.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.muhwyndhamhp.basemodule.data.model.Movie
import io.muhwyndhamhp.basemodule.data.model.Review
import io.muhwyndhamhp.basemodule.local.dao.MovieDao
import io.muhwyndhamhp.basemodule.util.TypeConverter

@Database(entities = [Movie::class, Review::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}