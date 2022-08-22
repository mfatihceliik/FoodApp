package com.muhammedfatihcelik.foodorder.ui.basket

import androidx.lifecycle.*
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.basket.Basket
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketRequest
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketResponse
import com.muhammedfatihcelik.foodorder.data.entity.order.CompleteOrderRequest
import com.muhammedfatihcelik.foodorder.data.entity.order.CompleteOrderResponse
import com.muhammedfatihcelik.foodorder.data.entity.order.OrderResponse
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val localDataSource: LocalDataSource
): ViewModel() {


    fun getBasketByUserId() : LiveData<Resource<BasketResponse>> {
        return apiRepository.getBasketByUserId()
    }

    fun completeOrder(): LiveData<Resource<CompleteOrderResponse>> {
        return apiRepository.completeOrder(CompleteOrderRequest(localDataSource.getUser()!!.id))
    }


    fun calculateTotalPrice(basketList: List<Basket>?): String {
        var totalAmount: Double = 0.0
        basketList?.forEach {
            totalAmount += it.price * it.quantity

        }
        return ((totalAmount * 100.0).roundToInt() / 100.0).toString()
    }

}