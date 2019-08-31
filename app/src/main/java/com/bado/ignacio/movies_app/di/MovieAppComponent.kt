package com.bado.ignacio.movies_app.di

import com.bado.ignacio.movies_app.data.remote.MovieService
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface MovieAppComponent {

    fun getMovieService(): MovieService
}