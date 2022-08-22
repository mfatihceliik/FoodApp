package com.muhammedfatihcelik.foodorder.data.entity.common

import com.google.gson.annotations.SerializedName

class SuccessResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
) {
}