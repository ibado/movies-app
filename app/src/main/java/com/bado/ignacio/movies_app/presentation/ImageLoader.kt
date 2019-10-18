package com.bado.ignacio.movies_app.presentation

import android.content.Context
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, imageView: ImageView, context: Context)
}
