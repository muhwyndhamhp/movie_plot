package io.muhwyndhamhp.moviedb.network

data class GeneralResponse<T>(
    val page: Int? = 0,
    val results: T? = null
)