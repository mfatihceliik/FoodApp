package com.muhammedfatihcelik.foodorder.data

import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressRequest
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketRequest
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginRequest
import com.muhammedfatihcelik.foodorder.data.entity.order.CompleteOrderRequest
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.data.remote.RemoteDataSource
import com.muhammedfatihcelik.foodorder.utils.performNetworkOperation
import com.muhammedfatihcelik.foodorder.utils.performNetworkOperationWithToken
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    // LOGIN
    fun login(loginRequest: LoginRequest) = performNetworkOperationWithToken(
        call = {
            remoteDataSource.postLogin(loginRequest)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    // TOKEN
    fun checkToken(): String? {
        return localDataSource.getToken()
    }

    // USERID
    private fun getUserId(): Int {
        //return localDataSource.getUserId()
        return localDataSource.getUser()!!.id
    }

    // RESTAURANT
    fun getAllRestaurants() = performNetworkOperation { remoteDataSource.getAllRestaurant() }
    fun getRestaurantByPoint()  = performNetworkOperation { remoteDataSource.getRestaurantByPoint() }

    // FOOD
    fun getByRestaurantId(id: Int) = performNetworkOperation { remoteDataSource.getByRestaurantId(id = id) }

    // BASKET
    fun getBasketByUserId() = performNetworkOperation { remoteDataSource.getBasketByUserId(id = getUserId()) }
    fun addToBasket(basketRequest: BasketRequest) = performNetworkOperation { remoteDataSource.addToBasket(basketRequest = basketRequest) }

    // ORDER
    fun completeOrder(completeOrderRequest: CompleteOrderRequest) = performNetworkOperation { remoteDataSource.completeOrder(completeOrderRequest = completeOrderRequest) }

    // ADDRESS
    fun getAllCities() = performNetworkOperation { remoteDataSource.getAllCities() }
    fun getAllDistrictsByCityId(cityId: Int) = performNetworkOperation { remoteDataSource.getAllDistrictsByCityId(cityId = cityId) }
    fun getAddressByUserId() = performNetworkOperation { remoteDataSource.getAddressByUserId(userId = getUserId()) }
    fun addNewAddress(address: Address) = performNetworkOperation { remoteDataSource.addNewAddress(address = address) }
    fun getByCurrentAddress() = performNetworkOperation { remoteDataSource.getByCurrentAddress(userId = getUserId()) }
    fun updateUserAddress(address: Address) = performNetworkOperation { remoteDataSource.updateUserAddress(address = address) }

}