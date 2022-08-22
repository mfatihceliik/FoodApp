package com.muhammedfatihcelik.foodorder.data.entity.basket

import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("data")
    val data: List<Basket>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
