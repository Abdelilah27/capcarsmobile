package com.capgemini.capcars.data.network

import android.content.Context
import com.capgemini.capcars.R

sealed class NetworkError : Exception() {

    object NoInternetConnection : NetworkError()
    data class ApiError(val code: Int, val errorMessage: String?) : NetworkError()
    object Timeout : NetworkError()
    object Unknown : NetworkError()

    fun getMessage(context: Context): String = when (this) {
        is NoInternetConnection -> context.getString(R.string.no_internet_connection)
        is ApiError -> context.getString(R.string.api_error, code, errorMessage)
        is Timeout -> context.getString(R.string.timeout)
        is Unknown -> context.getString(R.string.unknown_error)
    }
}
