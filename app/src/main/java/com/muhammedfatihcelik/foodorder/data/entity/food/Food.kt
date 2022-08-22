package com.muhammedfatihcelik.foodorder.data.entity.food

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    @SerializedName("id")
    val id: Int,
    @SerializedName("restaurant_id")
    val restaurantId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image_path")
    val imagePath: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("ingredients")
    val ingredients: String,

): Parcelable {
    var quantity: Int = 1
}