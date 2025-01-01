package com.capgemini.capcars.data.network

/**
 * Sealed class representing the result of an operation.
 * Can either be a success with data or a failure with an error.
 */

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: NetworkError) : Result<Nothing>()
}