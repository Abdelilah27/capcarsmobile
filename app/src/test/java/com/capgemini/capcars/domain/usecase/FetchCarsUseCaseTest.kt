package com.capgemini.capcars.domain.usecase

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.data.network.Result
import com.capgemini.capcars.data.repository.CarRepository
import com.capgemini.capcars.utils.MainDispatcherRule
import com.capgemini.capcars.utils.runTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class FetchCarsUseCaseTest {

    // This rule ensures that the dispatcher used in tests is properly handled, allowing coroutine-based tests to run on the correct dispatcher
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // Mocking the CarRepository
    private val repository = mockk<CarRepository>()

    // Instantiating the FetchCarsUseCase with the mocked repository and a dispatcher (IO thread for background work)
    private val useCase = FetchCarsUseCaseImpl(repository, Dispatchers.IO)

    // Sample list of valid cars used for testing purposes
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
        ),
        CarItem(
            id = 3,
            model = "Model 3",
            brand = "Brand C",
            year = 2022,
            mpg = "28",
            hp = 200,
            perf = "8.0",
            image = "https://example.com/car3.jpg",
            processedPerf = "Very High"
        )
    )

    @Test
    fun `should emit success and return list of cars when cars are fetched successfully`() =
        runTest {
            // Mocking the repository to return a successful result with the valid cars list
            coEvery { repository.getCars() } returns Result.Success(validCars)

            // Invoking the use case and capturing the emitted value after filtering out loading state
            val actual = async { useCase().filter { it !is FetchCarState.Loading }.first() }

            // Asserting that the emitted state is success and the list of cars matches the expected result
            assertEquals(FetchCarState.Success(validCars), actual.await())
        }

    @Test
    fun `should emit loading before fetching starts`() = runTest {
        coEvery { repository.getCars() } returns Result.Success(validCars)

        // Checking the first emitted value, which should be the loading state
        val actual = async { useCase().first() }

        // Asserting that the first state emitted is "Loading"
        assertEquals(FetchCarState.Loading, actual.await())
    }

    @Test
    fun `should emit error when fetching cars fails`() = runTest {
        // Mocking the repository to return a failure with a network error
        coEvery { repository.getCars() } returns Result.Failure(NetworkError.Unknown)

        val actual = async { useCase().filter { it !is FetchCarState.Loading }.first() }

        // Asserting that the emitted state is an error
        assertEquals(FetchCarState.Error(NetworkError.Unknown), actual.await())
    }

    @Test
    fun `should emit error when an exception occurs during fetching cars`() = runTest {
        // Mocking the repository to throw a runtime exception when fetching cars
        coEvery { repository.getCars() } answers { throw RuntimeException() }

        val actual = async { useCase().filter { it !is FetchCarState.Loading }.first() }

        // Asserting that the emitted state is an error due to an exception occurring
        assertEquals(FetchCarState.Error(NetworkError.Unknown), actual.await())
    }
}