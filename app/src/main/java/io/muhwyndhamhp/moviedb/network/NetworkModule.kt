package io.muhwyndhamhp.moviedb.network

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    val networkModule = module {
        factory { provideTMDBApi(get()) }
        single { provideRetrofit() }

    }

    private fun provideTMDBApi(retrofit: Retrofit) = retrofit.create(TMDBApi::class.java)

    private fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}