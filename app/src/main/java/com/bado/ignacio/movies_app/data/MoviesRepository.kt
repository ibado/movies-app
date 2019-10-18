package com.bado.ignacio.movies_app.data

import com.bado.ignacio.movies_app.di.ApplicationModule.MoviesLocalDataSource
import com.bado.ignacio.movies_app.di.ApplicationModule.MoviesRemoteDataSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MoviesLocalDataSource private val local: MoviesDataSource,
    @MoviesRemoteDataSource private val remote: MoviesDataSource
) : MoviesDataSource {

    override suspend fun getTopRated(page: Int): List<Movie> {
        return remote.getTopRated(page)
    }

    override suspend fun getUpcoming(page: Int): List<Movie> {
        return remote.getUpcoming(page)
    }

    override suspend fun getPopular(page: Int): List<Movie> {
        return remote.getPopular(page)
    }
}