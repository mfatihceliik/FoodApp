package com.muhammedfatihcelik.foodorder.data.remote

import com.muhammedfatihcelik.foodorder.data.entity.address.*
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketRequest
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketResponse
import com.muhammedfatihcelik.foodorder.data.entity.common.BaseResponse
import com.muhammedfatihcelik.foodorder.data.entity.food.FoodResponse
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginRequest
import com.muhammedfatihcelik.foodorder.data.entity.login.LoginResponse
import com.muhammedfatihcelik.foodorder.data.entity.order.CompleteOrderRequest
import com.muhammedfatihcelik.foodorder.data.entity.order.CompleteOrderResponse
import com.muhammedfatihcelik.foodorder.data.entity.restaurants.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*

interface FoodOrderApiService {

    // USER
    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    // RESTAURANTS
    @GET("restaurant/getAll")
    suspend fun getAllRestaurants(): Response<RestaurantResponse>
    @GET("restaurant/getByBestRestaurantPoint")
    suspend fun getByPoint(): Response<RestaurantResponse>

    // FOOD
    @GET("food/getByRestaurantId/{id}")
    suspend fun getByRestaurantId(@Path("id") id: Int): Response<FoodResponse>

    // BASKET
    @GET("basket/getByUserId/{id}")
    suspend fun getBasketByUserId(@Path("id") id: Int): Response<BasketResponse>
    @POST("basket/add")
    suspend fun addToBasket(@Body basketRequest: BasketRequest): Response<BasketResponse>
    @PUT("basket/completeOrder")
    suspend fun completeOrder(@Body completeOrderRequest: CompleteOrderRequest): Response<CompleteOrderResponse>

    // ORDER

    // Address
    @GET("city/getAll")
    suspend fun getAllCities(): Response<CityResponse>
    @GET("district/getByCityId/{id}")
    suspend fun getAllDistrictsByCityId(@Path("id") cityId: Int): Response<DistrictResponse>
    @GET("address/getByUserId/{id}")
    suspend fun getAddressByUserId(@Path("id") userId: Int): Response<AddressResponse>
    @POST("address/add")
    suspend fun addNewAddress(@Body address: Address): Response<BaseResponse>
    @GET("address/getByCurrentAddress/{id}")
    suspend fun getByCurrentAddress(@Path("id") userId: Int): Response<AddressDtoResponse>
    @PUT("address/update/{id}")
    suspend fun updateUserAddress(@Body address: Address): Response<BaseResponse>


}