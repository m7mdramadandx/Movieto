package com.ramadan.movieto.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.ramadan.movieto.data.model.MovieTable
import com.ramadan.movieto.data.room.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository {

    companion object {
        private var movieDatabase: MovieDatabase? = null

        fun insertMovie(context: Context, movie: MovieTable) {
            movieDatabase = MovieDatabase.getInstance(context)
            // Coroutines is used to perform asynchronous tasks
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().insert(movie) }
        }

        fun retrieveMovies(context: Context): LiveData<MutableList<MovieTable>> {
            movieDatabase = MovieDatabase.getInstance(context)
            return movieDatabase!!.movieDao().retrieveMovies()
        }

        fun deleteAll(context: Context) {
            movieDatabase = MovieDatabase.getInstance(context)
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().deleteAll() }
        }

        fun deleteMovie(context: Context, movie: MovieTable) {
            movieDatabase = MovieDatabase.getInstance(context)
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().delete(movie) }
        }
    }

}