package com.bado.ignacio.movies_app.data.remote.movies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopular(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcoming(@Query("page") page: Int): Call<MovieResponse>
}