package com.capgemini.capcars.data.repository

import com.capgemini.capcars.data.datasource.CarRemoteDataSource
import com.capgemini.capcars.data.network.CarItem
import javax.inject.Inject
import com.capgemini.capcars.data.network.Result

interface CarRepository {
    suspend fun getCars(): Result<List<CarItem>>
}

class CarRepositoryImpl @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : CarRepository {

    override suspend fun getCars(): Result<List<CarItem>> {
        return carRemoteDataSource.fetchCars()
    }
}
