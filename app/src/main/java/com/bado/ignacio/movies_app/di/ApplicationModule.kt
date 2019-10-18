package com.bado.ignacio.movies_app.di

import com.bado.ignacio.movies_app.data.MoviesDataSource
import com.bado.ignacio.movies_app.data.MoviesRepository
import com.bado.ignacio.movies_app.data.remote.MovieService
import com.bado.ignacio.movies_app.data.remote.MoviesRemoteDataSource
import com.bado.ignacio.movies_app.presentation.GlideImageLoader
import com.bado.ignacio.movies_app.presentation.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module(includes = [ApplicationModule.ApplicationModuleBinds::class])
object ApplicationModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MoviesRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class MoviesLocalDataSource

    @JvmStatic
    @MoviesRemoteDataSource
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService): MoviesDataSource {
        return MoviesRemoteDataSource(movieService)
    }

    @JvmStatic
    @MoviesLocalDataSource
    @Provides
    fun provideMovieLocalDataSource(): MoviesDataSource {
        return com.bado.ignacio.movies_app.data.local.MoviesLocalDataSource()
    }

    @Module
    abstract class ApplicationModuleBinds {

        @Binds
        abstract fun bindRepository(repo: MoviesRepository): MoviesDataSource

        @Binds
        abstract fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
    }
}