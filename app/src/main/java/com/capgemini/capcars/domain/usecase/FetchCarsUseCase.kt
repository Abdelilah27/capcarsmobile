package com.capgemini.capcars.domain.usecase

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.data.network.Result
import com.capgemini.capcars.data.repository.CarRepository
import com.capgemini.capcars.domain.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Interface representing the use case for fetching car data.
 * The `invoke` function is used to execute the use case logic.
 */

interface FetchCarsUseCase {
    operator fun invoke(): Flow<FetchCarState>
}

/**
 * Implementation of the FetchCarsUseCase interface.
 * Handles fetching car data by interacting with the repository.
 */

class FetchCarsUseCaseImpl @Inject constructor(
    private val repository: CarRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : FetchCarsUseCase {
    override fun invoke() = flow {
        emit(FetchCarState.Loading)
        when (val result = repository.getCars()) {
            is Result.Success -> {
                emit(FetchCarState.Success(result.data))
            }
            is Result.Failure -> {
                emit(FetchCarState.Error(result.error))
            }
        }
    }.flowOn(dispatcher) // Run this flow on the specified dispatcher (IO)
        .catch {
        emit(FetchCarState.Error(NetworkError.Unknown))
    }
}

/**
 * Sealed interface representing the states of fetching car data.
 * Includes loading, success, and error states.
 */

sealed interface FetchCarState {
    object Loading : FetchCarState
    data class Success(val cars: List<CarItem>) : FetchCarState
    data class Error(val error: NetworkError) : FetchCarState
}