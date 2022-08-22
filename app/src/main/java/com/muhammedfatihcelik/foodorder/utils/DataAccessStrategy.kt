package com.muhammedfatihcelik.foodorder.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginResponse
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import kotlinx.coroutines.Dispatchers


fun <T> performNetworkOperationWithToken (call: suspend() -> Resource<T>, saveToken: (token: String) -> Unit): LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if(networkCall.status == Resource.Status.SUCCESS) {
            val message = networkCall.message!!
            val data = networkCall.data!!
            if(data is LoginResponse) {
                saveToken(data.token)
            }
            emit(Resource.success(data, message))
        } else if(networkCall.status == Resource.Status.ERROR) {
            val message = networkCall.message!!
            emit(Resource.error(message = message))
        }
    }
}

fun <T> performNetworkOperation (call: suspend() -> Resource<T>): LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if(networkCall.status == Resource.Status.SUCCESS) {
            val message = networkCall.message!!
            val data = networkCall.data!!
            emit(Resource.success(data, message))
        } else if(networkCall.status == Resource.Status.ERROR) {
            val message = networkCall.message!!
            emit(Resource.error(message = message))
        }
    }
}