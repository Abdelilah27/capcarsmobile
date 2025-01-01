package com.capgemini.capcars.data.network

import com.google.gson.annotations.SerializedName

/**
 * Represents a car item retrieved from the remote API.
 * Includes details such as brand, model, year, performance, and more.
 */

data class CarItem(
    @SerializedName("id") val id: Int,
    @SerializedName("brand") val brand: String,
    @SerializedName("model") val model: String,
    @SerializedName("year") val year: Int,
    @SerializedName("mpg") val mpg: String,
    @SerializedName("hp") val hp: Int,
    @SerializedName("perf") val perf: String,
    @SerializedName("image") val image: String,
    val processedPerf: String
)