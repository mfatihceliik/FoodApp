package com.muhammedfatihcelik.foodorder.data.entity.restaurants


import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    @SerializedName("data")
    val data: List<Restaurant>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)