package com.muhammedfatihcelik.foodorder.data.entity.address

import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("address_title")
    val addressTitle: String,
    @SerializedName("open_address")
    val openAddress: String
)
