package com.bado.ignacio.movies_app.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bado.ignacio.movies_app.R
import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.injector
import com.bado.ignacio.movies_app.presentation.home.MainActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MovieDetailActivity::class.java.name
    }

    private val viewModel: MovieDetailViewModel by viewModels {
        injector.getMovieDetailViewModelFactory()
    }

    private val playerView by lazy {
        findViewById<YouTubePlayerView>(R.id.yt_player)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie: Movie = intent.getSerializableExtra(MainActivity.MOVIE_EXTRA) as Movie
        Log.d(TAG, "movie: $movie")

        viewModel.videoCode.observe(this, Observer {
            lifecycle.addObserver(playerView)
            playerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(it, 0f)
                }
            })
        })

        viewModel.getVideos(movieId = movie.id)
    }

    override fun onStop() {
        super.onStop()
        playerView.release()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> { onBackPressed(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
