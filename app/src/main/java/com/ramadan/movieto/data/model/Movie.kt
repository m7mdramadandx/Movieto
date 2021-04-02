package com.ramadan.movieto.data.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Movie(
    val dates: Dates,
    val page: Int,
    val results: MutableList<MovieTable>,
    val total_pages: Int,
    val total_results: Int
) : Serializable {
    @Keep
    data class Dates(
        val maximum: String,
        val minimum: String
    ) : Serializable

}

