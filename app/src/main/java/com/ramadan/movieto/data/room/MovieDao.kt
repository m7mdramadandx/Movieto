package com.ramadan.movieto.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramadan.movieto.data.model.MovieTable


@Dao
interface MovieDao {
    // Data Access Object Class for mapping SQL quires

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteTable: MovieTable)

    @Query("SELECT * FROM MOVIE ")
    fun retrieveNotes(): LiveData<MutableList<MovieTable>>

    @Query("SELECT * FROM MOVIE WHERE ID=:id")
    fun getNote(id: Int): LiveData<MovieTable>

    @Update
    suspend fun update(noteTable: MovieTable)

    @Query("DELETE FROM MOVIE")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(noteTable: MovieTable)
}