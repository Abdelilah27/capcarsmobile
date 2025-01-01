package com.capgemini.capcars.domain.usecase

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.data.network.Result
import com.capgemini.capcars.data.repository.CarRepository
import com.capgemini.capcars.domain.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Retrieves the list of cars.
 */

class FetchCarsUseCase @Inject constructor(
    private val repository: CarRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke() = flow {
        emit(FetchCarState.Loading)
        when (val result = repository.getCars()) {
            is Result.Success -> {
                emit(FetchCarState.Success(result.data))
            }
            is Result.Failure -> {
                emit(FetchCarState.Error(result.error))
            }
        }
    }.flowOn(dispatcher).catch {
        emit(FetchCarState.Error(NetworkError.Unknown))
    }
}

sealed interface FetchCarState {
    object Loading : FetchCarState
    data class Success(val cars: List<CarItem>) : FetchCarState
    data class Error(val error: NetworkError) : FetchCarState
}