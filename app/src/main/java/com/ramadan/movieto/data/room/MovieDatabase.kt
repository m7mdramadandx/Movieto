package com.ramadan.movieto.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ramadan.movieto.data.model.GenresConverter
import com.ramadan.movieto.data.model.MovieTable

@Database(entities = [MovieTable::class], version = 2, exportSchema = false)
@TypeConverters(GenresConverter::class)

abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    // that's how i've made an instance of database one time
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "MOVIE_DB")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }

}