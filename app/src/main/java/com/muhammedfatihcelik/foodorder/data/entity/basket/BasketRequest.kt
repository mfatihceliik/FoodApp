package com.muhammedfatihcelik.foodorder.data.entity.basket

import com.google.gson.annotations.SerializedName

data class BasketRequest(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("food_id")
    val food_id: Int,
    @SerializedName("restaurant_id")
    val restaurant_id: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("is_completed")
    val isCompleted: Boolean = false
)
