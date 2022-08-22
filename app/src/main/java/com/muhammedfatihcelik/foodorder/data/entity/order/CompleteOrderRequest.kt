package com.muhammedfatihcelik.foodorder.data.entity.order

import com.google.gson.annotations.SerializedName

data class CompleteOrderRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("is_completed")
    val isCompleted: Boolean = true
)
