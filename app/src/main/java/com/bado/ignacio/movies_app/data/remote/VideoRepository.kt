package com.bado.ignacio.movies_app.data.remote

import com.bado.ignacio.movies_app.data.Video
import com.bado.ignacio.movies_app.data.VideosDataSource
import com.bado.ignacio.movies_app.di.ApplicationModule.VideosLocalDataSource
import com.bado.ignacio.movies_app.di.ApplicationModule.VideosRemoteDataSource
import javax.inject.Inject

class VideoRepository @Inject constructor(
    @VideosLocalDataSource private val local: VideosDataSource,
    @VideosRemoteDataSource private val remote: VideosDataSource
) : VideosDataSource {

    override suspend fun getMovieVideos(movieId: Int): List<Video> {
        return remote.getMovieVideos(movieId)
    }
}