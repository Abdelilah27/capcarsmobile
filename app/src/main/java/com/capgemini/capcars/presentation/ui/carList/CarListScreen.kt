package com.capgemini.capcars.presentation.ui.carList

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CarListScreen(navController: NavController, viewModel: CarListViewModel = hiltViewModel()) {
    val carListState by viewModel.state.collectAsState()

    when (val state = carListState) {

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
            Log.d("CarListScreen", "Success: ${state.cars.joinToString(", ") { it.model }}")
        }
    }
}