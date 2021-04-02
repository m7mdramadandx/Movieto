package com.ramadan.movieto.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MOVIE")
data class MovieTable(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    val id: Int,

    @ColumnInfo(name = "ADULT")
    val adult: Boolean,

    @ColumnInfo(name = "BACKDROP_PATH")
    val backdrop_path: String,

//    @Ignore
//    @ColumnInfo(name = "GENRE_IDS")
//    val genre_ids: List<Int> = listOf(),

    @ColumnInfo(name = "ORIGINAL_LANGUAGE")
    val original_language: String,

    @ColumnInfo(name = "ORIGINAL_TITLE")
    val original_title: String,

    @ColumnInfo(name = "OVERVIEW")
    val overview: String,

    @ColumnInfo(name = "POPULARITY")
    val popularity: Double,

    @ColumnInfo(name = "POSTER_PATH")
    val poster_path: String,

    @ColumnInfo(name = "RELEASE_DATE")
    val release_date: String,

    @ColumnInfo(name = "TITLE")
    val title: String,

    @ColumnInfo(name = "VIDEO")
    val video: Boolean,

    @ColumnInfo(name = "VOTE_AVERAGE")
    val vote_average: Double,

    @ColumnInfo(name = "VOTE_COUNT")
    val vote_count: Int,

    @ColumnInfo(name = "IS_FAVORITE")
    var is_favorite: Boolean

) : Serializable {
    constructor() : this(
        0, false, "", "",
        "", "", 0.0, "", "", "",
        false, 0.0, 0, false
    )
}

