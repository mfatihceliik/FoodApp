package com.muhammedfatihcelik.foodorder.utils.listener

import androidx.lifecycle.LiveData
import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.common.BaseResponse
import com.muhammedfatihcelik.foodorder.utils.Resource

interface AddressChangeOnClickListener {
    fun onClick(address: Address)
}