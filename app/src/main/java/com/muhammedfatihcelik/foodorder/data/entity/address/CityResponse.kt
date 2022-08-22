package com.muhammedfatihcelik.foodorder.data.entity.address

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("data")
    val data: List<City>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
