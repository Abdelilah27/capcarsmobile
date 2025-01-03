package com.capgemini.capcars.data.network

import android.content.Context
import com.capgemini.capcars.R

/**
 * Sealed class representing different network error types.
 * Provides an error message based on the error type.
 */

sealed class NetworkError : Exception() {

    object NoInternetConnection : NetworkError()
    data class ApiError(val code: Int, val errorMessage: String?) : NetworkError()
    object Timeout : NetworkError()
    object Unknown : NetworkError()


    /**
     * Returns a user-friendly error message based on the error type.
     * @return A localized error message.
     */
    fun getMessageResource(): Int = when (this) {
        is NoInternetConnection -> R.string.no_internet_connection
        is ApiError -> R.string.api_error
        is Timeout -> R.string.timeout
        is Unknown -> R.string.unknown_error
    }
}
