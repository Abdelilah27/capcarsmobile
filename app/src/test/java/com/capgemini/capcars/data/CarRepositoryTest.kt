package com.capgemini.capcars.data

import com.capgemini.capcars.data.datasource.CarRemoteDataSource
import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.data.network.Result
import com.capgemini.capcars.data.repository.CarRepositoryImpl
import com.capgemini.capcars.utils.runTest
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CarRepositoryImplTest {

    // Mocking the CarRemoteDataSource
    private val carRemoteDataSource = mockk<CarRemoteDataSource>()

    // Creating an instance of the repository with the mocked data source
    private val repository = CarRepositoryImpl(carRemoteDataSource)

    // Defining a sample list of cars
    private val validCars = listOf(
        CarItem(
            id = 1,
            model = "Model 1",
            brand = "Brand A",
            year = 2021,
            mpg = "25",
            hp = 150,
            perf = "7.5",
            image = "https://example.com/car1.jpg",
            processedPerf = "Medium"
        ),
        CarItem(
            id = 2,
            model = "Model 2",
            brand = "Brand B",
            year = 2020,
            mpg = "30",
            hp = 180,
            perf = "6.5",
            image = "https://example.com/car2.jpg",
            processedPerf = "High"
        )
    )

    @Test
    fun `should return list of cars when getCars is successful`() =
        runTest {
            // Given: CarRemoteDataSource returns a successful result
            coEvery { carRemoteDataSource.fetchCars() } returns Result.Success(validCars)

            // When: we call getCars on the repository
            val result = repository.getCars()

            // Then: the result should be a Success with the validCars list
            assertEquals(Result.Success(validCars), result)
        }

    @Test
    fun `should return error when getCars fails`() = runTest {
        coEvery { carRemoteDataSource.fetchCars() } returns Result.Failure(NetworkError.Unknown)

        val result = repository.getCars()

        // The result should be a Failure with the NetworkError.Unknown
        assertEquals(Result.Failure(NetworkError.Unknown), result)
    }

    @Test
    fun `should return error when an exception occurs in getCars`() = runTest {
        coEvery { carRemoteDataSource.fetchCars() } throws RuntimeException("Network error")

        val result = repository.getCars()

        // The result should be a Failure with NetworkError.Unknown
        assertEquals(Result.Failure(NetworkError.Unknown), result)
    }
}