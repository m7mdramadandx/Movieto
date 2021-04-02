@file:Suppress("DEPRECATION")

package com.ramadan.movieto.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ramadan.movieto.data.model.MovieTable
import com.ramadan.movieto.data.repo.MovieRepository
import com.ramadan.movieto.utils.showMessage


class MovieViewModel : ViewModel() {

    fun addMovie(context: Context, result: MovieTable?) {
        result?.let {
            it.is_favorite = true
            MovieRepository.insertMovie(context, it)
        } ?: showMessage(context, "Failure")
    }

    fun retrieveMovies(context: Context): LiveData<MutableList<MovieTable>> =
        MovieRepository.retrieveMovies(context)

    fun removeMovie(context: Context, movieTable: MovieTable) {
        MovieRepository.deleteMovie(context, movieTable)
    }

}