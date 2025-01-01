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
     * @param context The context to fetch string resources.
     * @return A localized error message.
     */
    fun getMessage(context: Context): String = when (this) {
        is NoInternetConnection -> context.getString(R.string.no_internet_connection)
        is ApiError -> context.getString(R.string.api_error, code, errorMessage)
        is Timeout -> context.getString(R.string.timeout)
        is Unknown -> context.getString(R.string.unknown_error)
    }
}
