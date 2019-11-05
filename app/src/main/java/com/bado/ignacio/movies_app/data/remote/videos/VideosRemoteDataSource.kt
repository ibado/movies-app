package com.bado.ignacio.movies_app.data.remote.videos

import com.bado.ignacio.movies_app.data.Video
import com.bado.ignacio.movies_app.data.VideosDataSource
import javax.inject.Inject

class VideosRemoteDataSource @Inject constructor(
    private val videoService: VideoService
) : VideosDataSource {

    override suspend fun getMovieVideos(movieId: Int): List<Video> {
        return videoService.getVideos(movieId).results
    }
}