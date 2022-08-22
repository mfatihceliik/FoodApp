package com.muhammedfatihcelik.foodorder.data.entity.address

import com.google.gson.annotations.SerializedName

data class AddressResponse(
    @SerializedName("data")
    val data: List<Address>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)
