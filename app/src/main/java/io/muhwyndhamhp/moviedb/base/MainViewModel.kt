package io.muhwyndhamhp.moviedb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData<String?>(null)

    val userName = MutableLiveData<String?>(null)

    val savableUsername = MutableLiveData<String?>(null)

    fun setLoadingState(isLoading: Boolean) {
        loading.postValue(isLoading)
    }

    fun setErrorState(message: String?) {
        error.postValue(message)
    }

    fun setUserName(username: String?) {
        userName.postValue(username)
    }

    fun saveUsername(username: String) {
        savableUsername.postValue(username)
    }
}