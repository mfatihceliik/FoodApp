package com.muhammedfatihcelik.foodorder.data.entity.order

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Order(
    @SerializedName("id")
    val id: Int,
    @SerializedName("basket_id")
    val basketId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("food_id")
    val foodId: Int,
    @SerializedName("address_id")
    val addressId: Int,
    @SerializedName("date")
    val date: Date,
)