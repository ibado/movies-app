package com.bado.ignacio.movies_app.features.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import com.bado.ignacio.movies_app.BuildConfig
import com.bado.ignacio.movies_app.R
import com.bado.ignacio.movies_app.data.MoviesRepository
import com.bado.ignacio.movies_app.features.ImageLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(
            this,
            HomeViewModel.Companion.Factory(MoviesRepository())
        ).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // populars
        val popularRecyclerView: RecyclerView = findViewById(R.id.rv_popular)
        popularRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        val popularAdapter = createMovieAdapter()
        popularRecyclerView.adapter = popularAdapter

        // upcoming
        val upcomingRecyclerView: RecyclerView = findViewById(R.id.rv_upcoming)
        upcomingRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        val upcomingAdapter = createMovieAdapter()
        upcomingRecyclerView.adapter = upcomingAdapter

        // top rated
        val topRatedRecyclerView: RecyclerView = findViewById(R.id.rv_top_rated)
        topRatedRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        val topRatedAdapter = createMovieAdapter()
        topRatedRecyclerView.adapter = topRatedAdapter

        viewModel.populars.observe(this, Observer {
            Log.d("ibado", "populars: $it")
            if (it != null) {
                popularAdapter.addItems(it)
                popularAdapter.notifyDataSetChanged()
            }
        })

        viewModel.upcoming.observe(this, Observer {
            Log.d("ibado", "upcoming: $it")
            if (it != null) {
                upcomingAdapter.addItems(it)
                upcomingAdapter.notifyDataSetChanged()
            }
        })

        viewModel.topRated.observe(this, Observer {
            Log.d("ibado", "topRated: $it")
            if (it != null) {
                topRatedAdapter.addItems(it)
                topRatedAdapter.notifyDataSetChanged()
            }
        })

    }

    private fun createMovieAdapter(): MovieAdapter {
        return MovieAdapter(
            ArrayList(),
            object : ImageLoader {
                override fun loadImage(url: String, imageView: ImageView) {
                    Glide.with(this@MainActivity)
                        .load(BuildConfig.IMAGE_URL + url)
                        .apply(
                            RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .signature(ObjectKey(url))
                        ).into(imageView)
                }

            }
        )
    }
}
