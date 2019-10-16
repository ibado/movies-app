package com.bado.ignacio.movies_app.features.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import com.bado.ignacio.movies_app.BuildConfig
import com.bado.ignacio.movies_app.R
import com.bado.ignacio.movies_app.data.Movie
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

        val populars = findViewById<RecyclerView>(R.id.rv_popular)
        val upcoming = findViewById<RecyclerView>(R.id.rv_upcoming)
        val topRated = findViewById<RecyclerView>(R.id.rv_top_rated)

        configureRecyclerView(populars)
        configureRecyclerView(upcoming)
        configureRecyclerView(topRated)

        viewModel.populars.observe(this, Observer {
            populateMovies(populars.adapter as MovieAdapter, it)
        })

        viewModel.upcoming.observe(this, Observer {
            populateMovies(upcoming.adapter as MovieAdapter, it)
        })

        viewModel.topRated.observe(this, Observer {
            populateMovies(topRated.adapter as MovieAdapter, it)
        })

    }

    private fun populateMovies(adapter: MovieAdapter, movies: List<Movie>?) {
        if (movies != null) {
            adapter.addItems(movies)
            adapter.notifyDataSetChanged()
        }
    }

    private fun configureRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
        val adapter = createMovieAdapter()
        recyclerView.adapter = adapter
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
