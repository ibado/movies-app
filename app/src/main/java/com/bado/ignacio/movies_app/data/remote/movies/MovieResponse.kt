package com.bado.ignacio.movies_app.data.remote.movies

import com.bado.ignacio.movies_app.data.Movie

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val totalResults: Int,
    val totalPages: Int
)