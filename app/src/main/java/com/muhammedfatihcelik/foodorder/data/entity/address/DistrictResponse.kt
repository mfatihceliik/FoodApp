package com.muhammedfatihcelik.foodorder.data.entity.address

import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("data")
    val data: List<District>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)