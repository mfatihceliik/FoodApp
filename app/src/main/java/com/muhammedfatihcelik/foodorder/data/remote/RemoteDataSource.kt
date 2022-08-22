package com.muhammedfatihcelik.foodorder.data.remote

import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressRequest
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketRequest
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginRequest
import com.muhammedfatihcelik.foodorder.data.entity.order.CompleteOrderRequest
import com.muhammedfatihcelik.foodorder.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodOrderApiService: FoodOrderApiService,
): BaseDataSource() {
    // LOGIN
    suspend fun postLogin(loginRequest: LoginRequest) = getResult { foodOrderApiService.login(loginRequest = loginRequest) }

    // RESTAURANT
    suspend fun getAllRestaurant() = getResult { foodOrderApiService.getAllRestaurants() }
    suspend fun getRestaurantByPoint() = getResult { foodOrderApiService.getByPoint() }

    // FOOD
    suspend fun getByRestaurantId(id: Int) = getResult { foodOrderApiService.getByRestaurantId(id = id) }

    // BASKET
    suspend fun getBasketByUserId(id: Int) = getResult { foodOrderApiService.getBasketByUserId(id = id) }
    suspend fun addToBasket(basketRequest: BasketRequest) = getResult { foodOrderApiService.addToBasket(basketRequest = basketRequest) }

    // ORDER
    suspend fun completeOrder(completeOrderRequest: CompleteOrderRequest) = getResult { foodOrderApiService.completeOrder(completeOrderRequest = completeOrderRequest) }

    // ADDRESS
    suspend fun getAllCities() = getResult { foodOrderApiService.getAllCities() }
    suspend fun getAllDistrictsByCityId(cityId: Int) = getResult { foodOrderApiService.getAllDistrictsByCityId(cityId = cityId) }
    suspend fun getAddressByUserId(userId: Int) = getResult { foodOrderApiService.getAddressByUserId(userId = userId) }
    suspend fun addNewAddress(address: Address) = getResult { foodOrderApiService.addNewAddress(address = address) }
    suspend fun getByCurrentAddress(userId: Int) = getResult { foodOrderApiService.getByCurrentAddress(userId = userId) }
    suspend fun updateUserAddress(address: Address) = getResult { foodOrderApiService.updateUserAddress(address = address) }
}