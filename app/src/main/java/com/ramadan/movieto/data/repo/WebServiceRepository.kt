package com.ramadan.movieto.data.repo

import com.ramadan.movieto.data.api.ApiHelper

class WebServiceRepository(private val apiHelper: ApiHelper) {

    suspend fun retrieveNowPlaying(language: String, page: Int) =
        apiHelper.nowPlaying(language, page)

    suspend fun retrieveHotRated(language: String, page: Int) = apiHelper.hotRated(language, page)

    suspend fun searchMovie(query: String) = apiHelper.searchMovie(query)

}