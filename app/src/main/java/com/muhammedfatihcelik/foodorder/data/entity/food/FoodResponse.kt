package com.muhammedfatihcelik.foodorder.data.entity.food

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("data")
    val data: List<Food>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)