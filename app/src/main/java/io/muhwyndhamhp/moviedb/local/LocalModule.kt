package io.muhwyndhamhp.moviedb.local

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object LocalModule {
    val localModule = module {
        single { provideAppDatabase(androidApplication()) }
        single { provideMovieDao(get()) }
    }

    private fun provideMovieDao(database: AppDatabase) = database.movieDao()


    private fun provideAppDatabase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "FavouriteDatabase")
            .allowMainThreadQueries().build()
}