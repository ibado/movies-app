package com.bado.ignacio.movies_app.data

interface MoviesDataSource {

    suspend fun getTopRated(page: Int = 1): List<Movie>

    suspend fun getUpcoming(page: Int = 1): List<Movie>

    suspend fun getPopular(page: Int = 1): List<Movie>
}