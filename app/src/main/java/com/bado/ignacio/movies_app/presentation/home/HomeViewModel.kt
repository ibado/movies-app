package com.bado.ignacio.movies_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bado.ignacio.movies_app.data.Movie
import com.bado.ignacio.movies_app.data.MoviesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        viewModelScope.launch(Dispatchers.IO) {
            val populars = movieRepository.getPopular(1)
            _populars.postValue(populars)
        }
    }

    private fun fetchTopRated() {
        viewModelScope.launch(Dispatchers.IO) {
            val topRated = movieRepository.getTopRated(1)
            _topRated.postValue(topRated)
        }
    }

    private fun fetchUpcoming() {
        viewModelScope.launch(Dispatchers.IO) {
            val upcoming = movieRepository.getUpcoming(1)
            _upcoming.postValue(upcoming)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val movieRepository: MoviesDataSource) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(movieRepository) as T
        }
    }
}