package com.capgemini.capcars.data.repository

import com.capgemini.capcars.data.datasource.CarRemoteDataSource
import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import javax.inject.Inject
import com.capgemini.capcars.data.network.Result

/**
 * Interface defining the contract for the CarRepository.
 * Responsible for fetching car data.
 */

interface CarRepository {
    suspend fun getCars(): Result<List<CarItem>>
}

/**
 * Implementation of CarRepository.
 * Fetches car data from the remote data source.
 */

class CarRepositoryImpl @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : CarRepository {

    override suspend fun getCars(): Result<List<CarItem>> {
        return try {
            carRemoteDataSource.fetchCars()
        } catch (exception: Exception) {
            // Return a Failure result if there's an exception
            Result.Failure(NetworkError.Unknown)
        }
    }
}