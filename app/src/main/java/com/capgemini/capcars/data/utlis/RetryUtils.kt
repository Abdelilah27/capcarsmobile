package com.capgemini.capcars.data.utlis

import kotlinx.coroutines.delay

suspend fun <T> retryWithExponentialBackoff(
    maxRetries: Int = 3,
    initialDelayMillis: Long = 1000,
    maxDelayMillis: Long = 10000,
    factor: Double = 2.0,
    block: suspend () -> T // The suspend function to execute
): T {
    var currentDelay = initialDelayMillis
    var attempt = 0

    while (attempt < maxRetries) {
        try {
            return block()
        } catch (e: Exception) {
            attempt++
            if (attempt >= maxRetries) {
                throw e
            }

            delay(currentDelay)
            currentDelay =
                (currentDelay * factor).toLong().coerceAtMost(maxDelayMillis)
        }
    }
    // Should not reach here, just a fallback
    throw IllegalStateException("Should not reach here")
}
