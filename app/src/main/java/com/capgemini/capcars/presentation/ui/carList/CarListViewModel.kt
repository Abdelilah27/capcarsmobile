package com.capgemini.capcars.presentation.ui.carList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.capcars.data.network.CarItem
import com.capgemini.capcars.data.network.NetworkError
import com.capgemini.capcars.domain.usecase.FetchCarState
import com.capgemini.capcars.domain.usecase.FetchCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val fetchCarsUseCase: FetchCarsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<CarListState>(CarListState.NoState)
    val state: StateFlow<CarListState> = _state

    init {
        fetchCars()
    }

    @VisibleForTesting // TODO SCAT
    fun fetchCars() {
        viewModelScope.launch {
            _state.value = CarListState.Loading
            fetchCarsUseCase().collectLatest { state ->
                when (state) {
                    is FetchCarState.Loading -> {
                        _state.value = CarListState.Loading
                    }

                    is FetchCarState.Success -> {
                        _state.value = CarListState.Success(state.cars)
                    }

                    is FetchCarState.Error -> {
                        _state.value = CarListState.Error(state.error)
                    }
                }
            }
        }
    }
}

sealed class CarListState {
    object Loading : CarListState()
    data class Success(val cars: List<CarItem>) : CarListState()
    data class Error(val message: NetworkError) : CarListState()
    object NoState : CarListState()
}