package com.bado.ignacio.movies_app

import android.app.Activity

val Activity.injector get() = (application as AppComponent).component