package com.muhammedfatihcelik.foodorder.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val data: List<Order>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
