package com.bado.ignacio.movies_app.di

import com.bado.ignacio.movies_app.data.MoviesDataSource
import com.bado.ignacio.movies_app.data.MoviesRepository
import com.bado.ignacio.movies_app.data.VideosDataSource
import com.bado.ignacio.movies_app.data.remote.VideoRepository
import com.bado.ignacio.movies_app.data.remote.videos.VideosRemoteDataSource
import com.bado.ignacio.movies_app.data.remote.movies.MovieService
import com.bado.ignacio.movies_app.data.remote.movies.MoviesRemoteDataSource
import com.bado.ignacio.movies_app.data.remote.videos.VideoService
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

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class VideosRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class VideosLocalDataSource


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

    @JvmStatic
    @VideosRemoteDataSource
    @Provides
    fun provideVideoRemoteDataSource(videoService: VideoService): VideosDataSource {
        return VideosRemoteDataSource(videoService)
    }

    @JvmStatic
    @VideosLocalDataSource
    @Provides
    fun provideVideosLocalDataSource(): VideosDataSource {
        return com.bado.ignacio.movies_app.data.local.VideosLocalDataSource()
    }

    @Module
    abstract class ApplicationModuleBinds {

        @Binds
        abstract fun bindMovieRepository(repo: MoviesRepository): MoviesDataSource

        @Binds
        abstract fun bindVideoRepository(repo: VideoRepository): VideosDataSource

        @Binds
        abstract fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
    }
}