package io.muhwyndhamhp.basemodule.data

import io.muhwyndhamhp.basemodule.data.model.Movie
import io.muhwyndhamhp.basemodule.local.dao.MovieDao
import io.muhwyndhamhp.basemodule.network.TMDBApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class AppRepository(
    private val tmdbApi: TMDBApi,
    private val movieDao: MovieDao
) {


    fun getPopularMovies(apiKey: String, page: Int) =
        flow {
            emit(
                Result.success(
                    tmdbApi.getPopularMovies(apiKey, page)
                )
            )
        }.catch { emit(Result.failure(it)) }

    fun getUpcomingMovies(apiKey: String, page: Int) =
        flow { emit(Result.success(tmdbApi.getUpcomingMovies(apiKey, page))) }
            .catch { emit(Result.failure(it)) }

    fun getMovieReviews(movieId: Int, apiKey: String, page: Int) =
        flow { emit(Result.success(tmdbApi.getMovieReviews(movieId, apiKey, page))) }
            .catch { emit(Result.failure(it)) }

    fun getMovieDetail(movieId: Int, apiKey: String) =
        flow { emit(Result.success(tmdbApi.getMovieDetails(movieId, apiKey))) }
            .catch { emit(Result.failure(it)) }

    fun getFavouriteMovies() = movieDao.getAllMovies()

    fun insertFavouriteMovie(movie: Movie?) = movieDao.insertMovie(movie)

    fun removeFavouriteMovie(movie: Movie?) = movieDao.delete(movie)
}