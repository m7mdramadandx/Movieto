package com.ramadan.movieto.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramadan.movieto.data.model.MovieTable


@Dao
interface MovieDao {
    // Data Access Object Class for mapping SQL quires

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieTable: MovieTable)

    @Query("SELECT * FROM MOVIE ")
    fun retrieveMovies(): LiveData<MutableList<MovieTable>>

    @Query("DELETE FROM MOVIE")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(noteTable: MovieTable)
}