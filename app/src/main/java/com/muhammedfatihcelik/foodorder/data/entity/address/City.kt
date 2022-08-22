package com.muhammedfatihcelik.foodorder.data.entity.address

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class City(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("city")
    val city: String
)
