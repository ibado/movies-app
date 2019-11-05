package com.bado.ignacio.movies_app.data

import com.squareup.moshi.Json
import java.io.Serializable

data class Video(
    val id: String,
    @field:Json(name = "iso_639_1") val language: String, // ISO 639-1
    @field:Json(name = "iso_3166_1") val country: String, // ISO 3166-1
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
) : Serializable