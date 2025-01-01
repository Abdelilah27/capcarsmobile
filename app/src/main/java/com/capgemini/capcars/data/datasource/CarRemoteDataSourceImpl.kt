package com.capgemini.capcars.data.datasource

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.data.network.Result
import com.capgemini.capcars.data.service.CarService
import com.capgemini.capcars.data.utlis.retryWithExponentialBackoff
import okio.IOException
import retrofit2.HttpException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * Defines the contract and implementation for fetching car data from a remote source.
 * Handles network operations and error management.
 */

interface CarRemoteDataSource {
    suspend fun fetchCars(): Result<List<CarItem>>
}

class CarRemoteDataSourceImpl @Inject constructor(
    private val carService: CarService
) : CarRemoteDataSource {

    // Fetches car data with retry logic and error handling
    override suspend fun fetchCars(): Result<List<CarItem>> {
        return retryWithExponentialBackoff {
            try {
                val response = carService.fetchCars()
                Result.Success(response)
            } catch (e: IOException) {
                Result.Failure(NetworkError.NoInternetConnection)
            } catch (e: HttpException) {
                Result.Failure(NetworkError.ApiError(e.code(), e.message()))
            } catch (e: TimeoutException) {
                Result.Failure(NetworkError.Timeout)
            } catch (e: Exception) {
                Result.Failure(NetworkError.Unknown)
            }
        }
    }
}