package com.muhammedfatihcelik.foodorder.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.common.User
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginRequest
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginResponse
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository,
    private val localDataSource: LocalDataSource
): ViewModel() {

    fun login(email: String, password: String): LiveData<Resource<LoginResponse>> {
        return apiRepository.login(LoginRequest(email = email, password = password))
    }

    /*fun setUserId(id: Int) {
        localDataSource.setUserId(id = id)
    }*/

    fun setUser(user: User) {
        localDataSource.setUser(user = user)
    }
}