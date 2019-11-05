package com.bado.ignacio.movies_app.data

interface VideosDataSource {

    suspend fun getMovieVideos(movieId: Int): List<Video>
}