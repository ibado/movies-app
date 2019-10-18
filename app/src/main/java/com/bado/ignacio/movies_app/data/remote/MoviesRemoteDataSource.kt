package com.bado.ignacio.movies_app.data.remote

import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.data.MoviesDataSource
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) : MoviesDataSource {

    override suspend fun getTopRated(page: Int): List<Movie> {
        val topRated = movieService.getTopRated(page).execute().body()
        return topRated?.results ?: emptyList()
    }

    override suspend fun getUpcoming(page: Int): List<Movie> {
        val upcoming = movieService.getUpcoming(page).execute().body()
        return upcoming?.results ?: emptyList()
    }

    override suspend fun getPopular(page: Int): List<Movie> {
        val populars = movieService.getPopular(page).execute().body()
        return populars?.results ?: emptyList()
    }
}
