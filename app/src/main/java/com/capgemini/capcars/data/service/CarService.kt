package com.capgemini.capcars.data.service

import com.capgemini.capcars.data.network.CarItem
import retrofit2.http.GET

interface CarService {
    @GET("cars")
    suspend fun fetchCars(): List<CarItem>
}
