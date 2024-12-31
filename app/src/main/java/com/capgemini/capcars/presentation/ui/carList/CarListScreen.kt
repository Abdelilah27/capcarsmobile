package com.capgemini.capcars.presentation.ui.carList

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun CarListScreen(carListState: CarListState) {


    when (carListState) {

        is CarListState.Error -> {
            Timber.tag("CarListScreen").d("Error")
        }

        CarListState.Loading -> {
            Timber.tag("CarListScreen").d("Loading ...")
        }

        CarListState.NoState -> {
            Timber.tag("CarListScreen").d("NoState")
        }

        is CarListState.Success -> {
            Timber.tag("CarListScreen")
                .d("Success: ${carListState.cars.joinToString(", ") { it.model }}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CarListScreenPreview() {
    CarListScreen(CarListState.NoState)
}