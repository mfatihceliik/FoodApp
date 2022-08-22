package com.muhammedfatihcelik.foodorder.data.entity.order

import com.google.gson.annotations.SerializedName

data class CompleteOrderResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
