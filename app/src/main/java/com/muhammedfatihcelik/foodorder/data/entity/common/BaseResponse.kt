package com.muhammedfatihcelik.foodorder.data.entity.common

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
) {

}
