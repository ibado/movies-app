package com.bado.ignacio.movies_app.data.local

import com.bado.ignacio.movies_app.data.Video
import com.bado.ignacio.movies_app.data.VideosDataSource
import javax.inject.Inject

class VideosLocalDataSource @Inject constructor() : VideosDataSource {

    override suspend fun getMovieVideos(movieId: Int): List<Video> {
        return emptyList()
    }
}