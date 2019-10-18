package com.bado.ignacio.movies_app.presentation

import android.content.Context
import android.widget.ImageView
import com.bado.ignacio.movies_app.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import javax.inject.Inject

class GlideImageLoader @Inject constructor() : ImageLoader{

    override fun loadImage(url: String, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(BuildConfig.IMAGE_URL + url)
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .signature(ObjectKey(url))
            ).into(imageView)
    }
}