package com.muhammedfatihcelik.foodorder.data.entity.address

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class District(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("district")
    val district: String,
    @SerializedName("city_id")
    val cityId: Int
)
