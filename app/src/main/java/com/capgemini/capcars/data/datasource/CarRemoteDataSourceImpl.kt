package com.capgemini.capcars.data.datasource

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.service.CarService
import javax.inject.Inject


interface CarRemoteDataSource {
    suspend fun fetchCars(): List<CarItem>
}

class CarRemoteDataSourceImpl @Inject constructor(
    private val carService: CarService
) : CarRemoteDataSource {

    override suspend fun fetchCars(): List<CarItem> {
        return carService.fetchCars()
    }
}