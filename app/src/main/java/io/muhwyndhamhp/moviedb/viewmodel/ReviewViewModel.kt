package io.muhwyndhamhp.moviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.muhwyndhamhp.moviedb.BuildConfig
import io.muhwyndhamhp.moviedb.data.AppRepository
import io.muhwyndhamhp.moviedb.data.model.Review
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReviewViewModel(private val appRepository: AppRepository) : ViewModel() {

    val reviews = MutableLiveData<List<Review>>()

    val error = MutableLiveData<String?>(null)
    val loading = MutableLiveData(false)

    fun getReviews(movieId: Int) {
        loading.postValue(true)
        try {
            viewModelScope.launch {
                appRepository.getMovieReviews(movieId, BuildConfig.TMDB_API_KEY, 1)
                    .collect { response ->
                        if (response.isSuccess)
                            reviews.postValue(response.getOrNull()?.results?.map { it.copy(movieId = movieId) })
                        else error.postValue(
                            response.exceptionOrNull()?.message
                                ?: response.exceptionOrNull()?.localizedMessage
                                ?: "General Error"
                        )
                    }
            }
        } catch (e: Exception) {
            error.postValue(e.message ?: e.localizedMessage ?: "General Error")
        }
    }
}