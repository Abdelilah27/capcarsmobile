package com.capgemini.capcars.data.service

import com.capgemini.capcars.data.network.CarItem
import retrofit2.http.GET

/**
 * Service interface for making network requests to fetch car data.
 */

interface CarService {
    @GET("cars")
    suspend fun fetchCars(): List<CarItem>
}
