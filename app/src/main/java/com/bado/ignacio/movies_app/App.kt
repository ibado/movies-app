package com.bado.ignacio.movies_app

import android.app.Application
import com.bado.ignacio.movies_app.di.ApplicationComponent
import com.bado.ignacio.movies_app.di.DaggerApplicationComponent

class App : Application(), AppComponent {

    override val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

interface AppComponent {
    val component: ApplicationComponent
}