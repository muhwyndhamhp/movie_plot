package io.muhwyndhamhp.moviedb.network

import io.muhwyndhamhp.moviedb.data.model.Movie
import io.muhwyndhamhp.moviedb.data.model.Review
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): GeneralResponse<List<Movie>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): GeneralResponse<List<Movie>>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): GeneralResponse<List<Review>>
}