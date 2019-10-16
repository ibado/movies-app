package com.bado.ignacio.movies_app.data

import com.bado.ignacio.movies_app.data.local.MoviesLocalDataSource
import com.bado.ignacio.movies_app.data.remote.MovieService
import com.bado.ignacio.movies_app.data.remote.MoviesRemoteDataSource
import com.bado.ignacio.movies_app.di.DaggerMovieAppComponent
import com.bado.ignacio.movies_app.di.NetworkModule


class MoviesRepository : MoviesDataSource {

    private val local: MoviesDataSource = MoviesLocalDataSource()
    private val remote: MoviesDataSource by lazy {

        val movieService: MovieService =
            DaggerMovieAppComponent
                .builder()
                .build()
                .getMovieService()
        MoviesRemoteDataSource(movieService)
    }

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