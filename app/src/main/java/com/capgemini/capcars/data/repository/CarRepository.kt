package com.capgemini.capcars.data.repository

import com.capgemini.capcars.data.dataSource.CarRemoteDataSource
import com.capgemini.capcars.data.network.CarItem
import javax.inject.Inject

interface CarRepository {
    suspend fun getCars(): List<CarItem>
}

class CarRepositoryImpl @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : CarRepository {

    override suspend fun getCars(): List<CarItem> {
        return carRemoteDataSource.fetchCars()
    }
}
