package com.bado.ignacio.movies_app.features

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, imageView: ImageView)
}
