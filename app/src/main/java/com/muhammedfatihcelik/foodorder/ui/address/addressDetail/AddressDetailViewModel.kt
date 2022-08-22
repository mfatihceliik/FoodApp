package com.muhammedfatihcelik.foodorder.ui.address.addressDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressRequest
import com.muhammedfatihcelik.foodorder.data.entity.address.CityResponse
import com.muhammedfatihcelik.foodorder.data.entity.address.DistrictResponse
import com.muhammedfatihcelik.foodorder.data.entity.common.BaseResponse
import com.muhammedfatihcelik.foodorder.data.entity.common.SuccessResponse
import com.muhammedfatihcelik.foodorder.data.entity.common.User
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressDetailViewModel @Inject constructor(
    val apiRepository: ApiRepository,
    val localDataSource: LocalDataSource,
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    fun getCities(): LiveData<Resource<CityResponse>> {
        return apiRepository.getAllCities()
    }

    fun getDistrictsByCityId(cityId: Int): LiveData<Resource<DistrictResponse>> {
        return apiRepository.getAllDistrictsByCityId(cityId = cityId)
    }

    fun addNewAddress(address: Address): LiveData<Resource<BaseResponse>> {
        return apiRepository.addNewAddress(address)
    }

    fun updateUserAddress(address: Address): LiveData<Resource<BaseResponse>> {
        return apiRepository.updateUserAddress(address = address)
    }

    fun getUser(): User? {
        return localDataSource.getUser()
    }
}