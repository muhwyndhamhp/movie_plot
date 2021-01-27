package io.muhwyndhamhp.basemodule.network

import io.muhwyndhamhp.basemodule.BuildConfig
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
        .baseUrl(BuildConfig.TMDB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}