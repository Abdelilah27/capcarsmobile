package com.capgemini.capcars.domain.usecase

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.repository.CarRepository
import com.capgemini.capcars.domain.core.di.IODispatcher
import com.capgemini.capcars.domain.core.di.MainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
        val cars = repository.getCars()
        emit(FetchCarState.Success(cars))
    }.flowOn(dispatcher).catch { _ ->
        emit(FetchCarState.Error)
    }
}

sealed interface FetchCarState {
    object Loading : FetchCarState
    data class Success(val cars: List<CarItem>) : FetchCarState
    object Error : FetchCarState
}