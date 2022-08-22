package com.muhammedfatihcelik.foodorder.data.entity.login

import com.google.gson.annotations.SerializedName
import com.muhammedfatihcelik.foodorder.data.entity.common.User

data class LoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: User,
    @SerializedName("token")
    val token: String
)
