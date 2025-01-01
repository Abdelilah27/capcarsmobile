package com.capgemini.capcars.data.repository

import com.capgemini.capcars.data.datasource.CarRemoteDataSource
import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import javax.inject.Inject
import com.capgemini.capcars.data.network.Result

interface CarRepository {
    suspend fun getCars(): Result<List<CarItem>>
}

class CarRepositoryImpl @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : CarRepository {

    override suspend fun getCars(): Result<List<CarItem>> {
        return try {
            carRemoteDataSource.fetchCars()
        } catch (exception: Exception) {
            Result.Failure(NetworkError.Unknown)
        }
    }
}