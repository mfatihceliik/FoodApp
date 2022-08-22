package com.muhammedfatihcelik.foodorder.data.entity.address

import com.google.gson.annotations.SerializedName

data class AddressDtoResponse(
    @SerializedName("data")
    val data: AddressDto,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
