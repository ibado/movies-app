package com.bado.ignacio.movies_app.data.local

import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.data.MoviesDataSource
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor() : MoviesDataSource {

    override suspend fun getTopRated(page: Int): List<Movie> {
        return emptyList()
    }

    override suspend fun getUpcoming(page: Int): List<Movie> {
        return emptyList()
    }

    override suspend fun getPopular(page: Int): List<Movie> {
        return emptyList()
    }

}