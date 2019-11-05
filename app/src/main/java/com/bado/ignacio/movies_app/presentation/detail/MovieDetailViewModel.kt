package com.bado.ignacio.movies_app.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bado.ignacio.movies_app.data.VideosDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel(private val videosRepository: VideosDataSource) : ViewModel() {

    val videoCode: MutableLiveData<String> = MutableLiveData()

    fun getVideos(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = videosRepository.getMovieVideos(movieId)
            results.forEach {
                if (it.site == "YouTube") {
                    videoCode.postValue(it.key)
                }
                return@forEach
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val videosRepository: VideosDataSource
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailViewModel(videosRepository) as T
        }
    }

}