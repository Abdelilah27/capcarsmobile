package com.capgemini.capcars.presentation.ui.carList

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CarListScreen(carListState: CarListState) {


    when (carListState) {

        is CarListState.Error -> {
            Log.d("CarListScreen", "Error")
        }

        CarListState.Loading -> {
            Log.d("CarListScreen", "Loading ...")
        }

        CarListState.NoState -> {
            Log.d("CarListScreen", "NoState")
        }

        is CarListState.Success -> {
            Log.d("CarListScreen", "Success: ${carListState.cars.joinToString(", ") { it.model }}")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CarListScreenPreview() {
    CarListScreen(CarListState.NoState)
}