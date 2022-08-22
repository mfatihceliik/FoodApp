package com.muhammedfatihcelik.foodorder.utils

data class Resource <out T>(val data: T?, val message: String?, val status: Status) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> loading (data: T? = null): Resource<T> {
            return Resource(data = data, null, Status.LOADING)
        }

        fun <T> success (data: T, message: String) : Resource<T> {
            return Resource(data = data, message = message, Status.SUCCESS)
        }

        fun <T> error (message: String?, data: T? = null) : Resource<T> {
            return Resource(message = message, data = data, status = Status.ERROR)
        }

    }

}