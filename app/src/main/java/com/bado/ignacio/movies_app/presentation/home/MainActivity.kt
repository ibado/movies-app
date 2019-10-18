package com.bado.ignacio.movies_app.presentation.home

import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import com.bado.ignacio.movies_app.R
import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.injector

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels {
        injector.getHomeViewModelFactory()
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
            populateMovies(populars, it)
        })

        viewModel.upcoming.observe(this, Observer {
            populateMovies(upcoming, it)
        })

        viewModel.topRated.observe(this, Observer {
            populateMovies(topRated, it)
        })
    }

    private fun populateMovies(recyclerView: RecyclerView, movies: List<Movie>?) {
        if (movies != null) {
            val adapter = recyclerView.adapter as MovieAdapter
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
            injector.getImageLoader()
        )
    }
}
