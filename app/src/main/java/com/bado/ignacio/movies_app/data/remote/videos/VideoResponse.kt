package com.bado.ignacio.movies_app.data.remote.videos

import com.bado.ignacio.movies_app.data.Video

class VideoResponse(
    val id: Int,
    val results: List<Video>
)