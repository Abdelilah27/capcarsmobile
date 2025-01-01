package com.capgemini.capcars.presentation.ui.carList

import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.domain.usecase.FetchCarState
import com.capgemini.capcars.domain.usecase.FetchCarsUseCase
import com.capgemini.capcars.utils.MainDispatcherRule
import com.capgemini.capcars.utils.runTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CarListViewModelTest {

    // Rule to set the main dispatcher to a test dispatcher
    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    // Mocking the FetchCarsUseCase
    @Mock
    private lateinit var fetchCarsUseCase: FetchCarsUseCase

    // Instance of CarListViewModel
    private lateinit var carListViewModel: CarListViewModel

    @Before
    fun setUp() {
        carListViewModel = CarListViewModel(fetchCarsUseCase)
    }

    @Test
    fun `when fetchCars is called, then state is updated to Loading`() = runTest {
        // Mocking the use case to emit Loading state
        whenever(fetchCarsUseCase.invoke()).thenReturn(flow {
            emit(FetchCarState.Loading)
        })

        // Calling the fetchCars method
        carListViewModel.fetchCars()

        // Checking if the state is updated to Loading
        val state = carListViewModel.state.first()
        assertEquals(CarListState.Loading, state)
    }

    @Test
    fun `when fetchCars is called and returns Success, then state is updated to Success`() =
        runTest {
            // Mock data
            val carItems =
                listOf(CarItem(1, "Toyota", "Camry", 2020, "30", 200, "4.0", "image_url", "4.0"))

            // Mocking the use case to emit Success state with carItems
            whenever(fetchCarsUseCase.invoke()).thenReturn(flow {
                emit(FetchCarState.Success(carItems))
            })

            // Calling the fetchCars method
            carListViewModel.fetchCars()

            // Checking if the state is updated to Success and contains the correct data
            val state = carListViewModel.state.first()
            assertTrue(state is CarListState.Success && state.cars == carItems)
        }

    @Test
    fun `when fetchCars is called and returns Error, then state is updated to Error`() = runTest {
        // Mock error
        val error = NetworkError.Unknown

        // Mocking the use case to emit Error state with the error
        whenever(fetchCarsUseCase.invoke()).thenReturn(flow {
            emit(FetchCarState.Error(error))
        })

        // Calling the fetchCars method
        carListViewModel.fetchCars()

        // Checking if the state is updated to Error and contains the correct error message
        val state = carListViewModel.state.first()
        assertTrue(state is CarListState.Error && state.message == error)
    }
}