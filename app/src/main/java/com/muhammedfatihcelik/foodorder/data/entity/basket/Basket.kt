package com.muhammedfatihcelik.foodorder.data.entity.basket

import com.google.gson.annotations.SerializedName
import com.muhammedfatihcelik.foodorder.utils.FoodQuantity

data class Basket(
    @SerializedName("basket_id")
    val basketId: Int,
    @SerializedName("food_id")
    val foodId: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("food_image_path")
    val foodImagePath: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("restaurant_image_path")
    val restaurantImagePath: String,
    @SerializedName("point")
    val point: Double,
    @SerializedName("quantity")
    var quantity: Int
)
