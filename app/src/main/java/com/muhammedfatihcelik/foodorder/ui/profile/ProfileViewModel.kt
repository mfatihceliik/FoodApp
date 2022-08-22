package com.muhammedfatihcelik.foodorder.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.address.*
import com.muhammedfatihcelik.foodorder.data.entity.common.User
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val apiRepository: ApiRepository,
    val localDataSource: LocalDataSource
) : ViewModel() {

    fun getUserDetail(): User? {
        return localDataSource.getUser()
    }

    fun getByCurrentAddress(): LiveData<Resource<AddressDtoResponse>> {
        return apiRepository.getByCurrentAddress()
    }
}