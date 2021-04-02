package com.ramadan.movieto.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ramadan.movieto.data.repo.WebServiceRepository
import com.ramadan.movieto.utils.Resource
import kotlinx.coroutines.Dispatchers

class WebServiceViewModel(private val webServiceRepository: WebServiceRepository) : ViewModel() {

    fun retrieveNowPlaying(language: String, page: Int) = liveData(Dispatchers.Main) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(webServiceRepository.retrieveNowPlaying(language, page)))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error Occurred!"))
        }
    }

    fun retrieveTopRated(language: String, page: Int) = liveData(Dispatchers.Main) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(webServiceRepository.retrieveHotRated(language, page)))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error Occurred!"))
        }
    }

    fun searchMovie(query: String) = liveData(Dispatchers.Main) {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(webServiceRepository.searchMovie(query)))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error Occurred!"))
        }
    }

}