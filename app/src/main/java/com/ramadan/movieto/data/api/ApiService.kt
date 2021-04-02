package com.ramadan.movieto.data.api

import com.ramadan.movieto.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/now_playing?api_key=b85126b11110ad477526e51ce6b361b6")
    suspend fun retrieveNowPlaying(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Movie

    @GET("3/movie/top_rated?api_key=b85126b11110ad477526e51ce6b361b6")
    suspend fun retrieveHotRated(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Movie

    @GET("3/search/movie?api_key=b85126b11110ad477526e51ce6b361b6")
    suspend fun searchMovie(@Query("query") query: String): Movie

}