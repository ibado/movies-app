package com.bado.ignacio.movies_app.data.remote.videos

import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService {

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(@Path("movieId") movieId: Int): VideoResponse
}
