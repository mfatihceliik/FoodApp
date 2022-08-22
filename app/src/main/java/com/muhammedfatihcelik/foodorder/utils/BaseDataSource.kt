package com.muhammedfatihcelik.foodorder.utils

import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if(response.isSuccessful) {
                val body = response.body()
                if(body != null) {
                    return Resource.success(body, response.message())
                }
            }
            val errorBody = response.errorBody().toString()
            //val error: ErrorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
            return error("${response.code()} - $errorBody")

        }catch (exception: Exception){
            return error("Network Error: $exception")
        }
    }

    private fun <T> error (message: String): Resource<T> {
        return Resource.error(message)
    }
}