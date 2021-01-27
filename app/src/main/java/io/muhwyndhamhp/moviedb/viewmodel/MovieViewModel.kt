package io.muhwyndhamhp.moviedb.viewmodel

import androidx.lifecycle.*
import io.muhwyndhamhp.moviedb.BuildConfig
import io.muhwyndhamhp.moviedb.data.AppRepository
import io.muhwyndhamhp.moviedb.data.model.Movie
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(private val appRepository: AppRepository) : ViewModel() {

    val currentMovie = MutableLiveData<Movie>()

    val popularMovies = MutableLiveData<List<Movie>>()

    val upcomingMovies = MutableLiveData<List<Movie>>()

    val favouriteMovies: MutableLiveData<List<Movie>?> = liveData {
        val movieMediator = MediatorLiveData<List<Movie>?>()
        emitSource(movieMediator)
        movieMediator.addSource(appRepository.getFavouriteMovies()) { movieMediator.postValue(it) }
    } as MutableLiveData<List<Movie>?>

    val error = MutableLiveData<String?>(null)
    val loading = MutableLiveData(false)

    fun getPopular() {
        loading.postValue(true)
        try {
            viewModelScope.launch {
                appRepository.getPopularMovies(BuildConfig.TMDB_API_KEY, 1).collect {
                    if (it.isSuccess) popularMovies.postValue(it.getOrNull()?.results?.take(6))
                    else error.postValue(
                        it.exceptionOrNull()?.message ?: it.exceptionOrNull()?.localizedMessage
                        ?: "General Error"
                    )
                }
            }
        } catch (e: Exception) {
            error.postValue(e.message ?: e.localizedMessage ?: "General Error")
        }
    }

    fun getUpcoming() {
        loading.postValue(true)
        try {
            viewModelScope.launch {
                appRepository.getUpcomingMovies(BuildConfig.TMDB_API_KEY, 1).collect {
                    if (it.isSuccess) upcomingMovies.postValue(it.getOrNull()?.results)
                    else error.postValue(
                        it.exceptionOrNull()?.message ?: it.exceptionOrNull()?.localizedMessage
                        ?: "General Error"
                    )
                }
            }
        } catch (e: Exception) {
            error.postValue(e.message ?: e.localizedMessage ?: "General Error")
        }
    }

    fun getDetails(movie: Movie) {
        currentMovie.postValue(movie)
        loading.postValue(true)
        try {
            viewModelScope.launch {
                appRepository.getMovieDetail(movie.id, BuildConfig.TMDB_API_KEY).collect {
                    if (it.isSuccess) currentMovie.postValue(it.getOrNull() ?: currentMovie.value)
                    else error.postValue(
                        it.exceptionOrNull()?.message ?: it.exceptionOrNull()?.localizedMessage
                        ?: "General Error"
                    )
                }
            }
        } catch (e: Exception) {
            error.postValue(e.message ?: e.localizedMessage ?: "General Error")
        }
    }

    fun addFavourite(movie: Movie?) {
        viewModelScope.launch {
            appRepository.insertFavouriteMovie(movie)
        }
    }

    fun removeFavouriteMovie(movie: Movie?) {
        viewModelScope.launch {
            appRepository.removeFavouriteMovie(movie)
        }
    }
}