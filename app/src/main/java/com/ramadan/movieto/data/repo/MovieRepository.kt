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

        fun insertNote(context: Context, note: MovieTable) {
            movieDatabase = MovieDatabase.getInstance(context)
            // Coroutines is used to perform asynchronous tasks
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().insert(note) }
        }

        fun updateNote(context: Context, note: MovieTable) {
            movieDatabase = MovieDatabase.getInstance(context)
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().update(note) }
        }

        fun retrieveNotes(context: Context): LiveData<MutableList<MovieTable>> {
            movieDatabase = MovieDatabase.getInstance(context)
            return movieDatabase!!.movieDao().retrieveNotes()
        }

        fun getNote(context: Context, ID: Int): LiveData<MovieTable> {
            movieDatabase = MovieDatabase.getInstance(context)
            return movieDatabase!!.movieDao().getNote(ID)
        }

        fun deleteAll(context: Context) {
            movieDatabase = MovieDatabase.getInstance(context)
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().deleteAll() }
        }

        fun deleteNote(context: Context, note: MovieTable) {
            movieDatabase = MovieDatabase.getInstance(context)
            CoroutineScope(Dispatchers.IO).launch { movieDatabase!!.movieDao().delete(note) }
        }
    }

}