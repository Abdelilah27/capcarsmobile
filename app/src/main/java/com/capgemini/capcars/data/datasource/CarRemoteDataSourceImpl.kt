package com.capgemini.capcars.data.datasource

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.data.network.Result
import com.capgemini.capcars.data.service.CarService
import okio.IOException
import retrofit2.HttpException
import java.util.concurrent.TimeoutException
import javax.inject.Inject


interface CarRemoteDataSource {
    suspend fun fetchCars(): Result<List<CarItem>>
}

class CarRemoteDataSourceImpl @Inject constructor(
    private val carService: CarService
) : CarRemoteDataSource {

    override suspend fun fetchCars(): Result<List<CarItem>> {
        return try {
            val response = carService.fetchCars()
            Result.Success(response)
        } catch (e: IOException) {
            // Handle network errors like no internet
            Result.Failure(NetworkError.NoInternetConnection)
        } catch (e: HttpException) {
            // Handle HTTP errors (e.g., 404, 500)
            Result.Failure(NetworkError.ApiError(e.code(), e.message()))
        } catch (e: TimeoutException) {
            // Handle network timeout
            Result.Failure(NetworkError.Timeout)
        } catch (e: Exception) {
            // Handle any other unexpected errors
            Result.Failure(NetworkError.Unknown)
        }
    }
}