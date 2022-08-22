package com.muhammedfatihcelik.foodorder.data.entity.restaurants


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    @SerializedName("id")
    val id: Int,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("image_path")
    val imagePath: String,
    @SerializedName("point")
    val point: Double,
    @SerializedName("city")
    val city : String,
    @SerializedName("district")
    val district: String
): Parcelable