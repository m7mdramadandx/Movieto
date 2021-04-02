package com.ramadan.movieto.data.api


class ApiHelper(private val apiService: ApiService) {
    suspend fun nowPlaying(language: String, page: Int) =
        apiService.retrieveNowPlaying(language, page)

    suspend fun hotRated(language: String, page: Int) =
        apiService.retrieveHotRated(language, page)

    suspend fun searchMovie(query: String) = apiService.searchMovie(query)
}