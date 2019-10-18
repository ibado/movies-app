package com.bado.ignacio.movies_app.di

import android.content.Context
import com.bado.ignacio.movies_app.presentation.ImageLoader
import com.bado.ignacio.movies_app.presentation.home.HomeViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ): ApplicationComponent
    }

    fun getHomeViewModelFactory(): HomeViewModel.Factory

    fun getImageLoader(): ImageLoader
}