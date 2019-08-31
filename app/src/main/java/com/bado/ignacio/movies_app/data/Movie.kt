package com.bado.ignacio.movies_app.data

import com.squareup.moshi.Json
import java.io.Serializable

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "release_date") val releaseDate: String,
    val video: Boolean
) : Serializable