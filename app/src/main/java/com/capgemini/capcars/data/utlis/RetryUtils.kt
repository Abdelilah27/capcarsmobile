package com.capgemini.capcars.data.utlis

import kotlinx.coroutines.delay

/**
 * Retries a block of code with exponential backoff for a specified number of attempts.
 * The delay between each attempt increases exponentially, but is capped at a maximum value.
 *
 * @param maxRetries Maximum number of retry attempts. Default is 3.
 * @param initialDelayMillis The initial delay before the first retry. Default is 1000ms.
 * @param maxDelayMillis The maximum delay between retries. Default is 10000ms.
 * @param factor The factor by which the delay increases after each attempt. Default is 2.0.
 * @param block The suspend function to execute that will be retried on failure.
 * @return The result of the block execution if successful.
 * @throws Exception If the block fails after the maximum number of retries.
 */

suspend fun <T> retryWithExponentialBackoff(
    maxRetries: Int = 3,
    initialDelayMillis: Long = 1000,
    maxDelayMillis: Long = 10000,
    factor: Double = 2.0,
    block: suspend () -> T // The suspend function to execute
): T {
    var currentDelay = initialDelayMillis
    var attempt = 0

    // Retry block execution until maxRetries is reached
    while (attempt < maxRetries) {
        try {
            return block()
        } catch (e: Exception) {
            attempt++
            // If maxRetries reached, rethrow the exception
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
