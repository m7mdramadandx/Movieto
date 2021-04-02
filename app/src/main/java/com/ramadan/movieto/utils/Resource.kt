package com.ramadan.movieto.utils

import androidx.annotation.Keep
import com.ramadan.movieto.utils.ResponseStatus.*


@Keep
data class Resource<out T>(val status: ResponseStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(data: T?): Resource<T> =
            Resource(LOADING, data, null)

        fun <T> success(data: T): Resource<T> =
            Resource(SUCCESS, data, null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(ERROR, data, message)

    }
}