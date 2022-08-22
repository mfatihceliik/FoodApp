package com.muhammedfatihcelik.foodorder.data.entity.address

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("district_id")
    val districtId: Int,
    @SerializedName("address_title")
    val addressTitle: String,
    @SerializedName("open_address")
    val openAddress: String,
    @SerializedName("is_current_address")
    val isCurrentAddress: Boolean = false
): Parcelable
