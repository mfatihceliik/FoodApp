package com.muhammedfatihcelik.foodorder.ui.food.foodDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketRequest
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketResponse
import com.muhammedfatihcelik.foodorder.data.entity.food.Food
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    val apiRepository: ApiRepository,
    val localDataSource: LocalDataSource,
    var savedStateHandle: SavedStateHandle
): ViewModel() {

    //var food = savedStateHandle.get<Food>("food")!!

    private var _foodQuantity = MutableLiveData<Int>(0)
    val foodQuantity: LiveData<Int>
        get() = _foodQuantity

    fun foodAddToBasket(food: Food): LiveData<Resource<BasketResponse>> {
        return apiRepository.addToBasket(basketRequest = BasketRequest(localDataSource.getUser()!!.id, food.id, food.restaurantId, food.quantity))
    }

    fun increaseFoodQuantity(food: Food): String {
        if(food.quantity < 20){
            _foodQuantity.value = food.quantity + 1
            food.quantity = _foodQuantity.value!!
        }
        return _foodQuantity.value!!.toString()
    }
    fun decreaseFoodQuantity(food: Food): String {
        if(food.quantity > 1){
            _foodQuantity.value = food.quantity - 1
            food.quantity = _foodQuantity.value!!
        }
        return _foodQuantity.value!!.toString()
    }
}