package com.bado.ignacio.movies_app.di

import com.bado.ignacio.movies_app.data.remote.MovieService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface MovieAppComponent {

    fun getMovieService(): MovieService
}