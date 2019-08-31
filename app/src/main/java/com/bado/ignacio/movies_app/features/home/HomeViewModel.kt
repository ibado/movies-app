package com.bado.ignacio.movies_app.features.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.data.MoviesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MoviesDataSource) : ViewModel() {

    private val _populars: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _topRated: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _upcoming: MutableLiveData<List<Movie>> = MutableLiveData()

    val populars: LiveData<List<Movie>> get() = _populars
    val topRated: LiveData<List<Movie>> get() = _topRated
    val upcoming: LiveData<List<Movie>> get() = _upcoming

    init {
        fetchPopular()
        fetchTopRated()
        fetchUpcoming()
    }

    private fun fetchPopular() {
        CoroutineScope(Dispatchers.IO).launch {
            val popularMovies = async { movieRepository.getPopular(1) }
            _populars.postValue(popularMovies.await())
        }
    }

    private fun fetchTopRated() {
        CoroutineScope(Dispatchers.IO).launch {
            val topRated = async { movieRepository.getTopRated(1) }
            val result = topRated.await()
            _topRated.postValue(result)
        }
    }

    private fun fetchUpcoming() {
        CoroutineScope(Dispatchers.IO).launch {
            val upcoming = async { movieRepository.getUpcoming(1) }
            val result = upcoming.await()
            _upcoming.postValue(result)
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        class Factory(private val movieRepository: MoviesDataSource) : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(movieRepository) as T
            }
        }
    }
}