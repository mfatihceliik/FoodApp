package com.muhammedfatihcelik.foodorder.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.food.Food
import com.muhammedfatihcelik.foodorder.data.entity.food.FoodResponse
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.listener.FoodDetailOnClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    val apiRepository: ApiRepository,
    val localDataSource: LocalDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    //var restaurant = savedStateHandle.get<Restaurant>("restaurant")!!
    private var _foodQuantity = MutableLiveData<Int>(0)
    val foodQuantity: LiveData<Int>
    get() = _foodQuantity

    fun getFoodsByRestaurantId(restaurantId: Int): LiveData<Resource<FoodResponse>> {
        return apiRepository.getByRestaurantId(restaurantId)
    }

    fun foodQuantityIncrease (food: Food): Int {
        if(food.quantity < 20){
            _foodQuantity.value = food.quantity + 1
            food.quantity = _foodQuantity.value!!
        }
        return _foodQuantity.value!!
    }
    fun foodQuantityDecrease (food: Food): Int {
        if(food.quantity > 0){
            _foodQuantity.value = food.quantity - 1
            food.quantity = _foodQuantity.value!!
        }
        return _foodQuantity.value!!
    }

    fun goFoodDetailFragment(adapter: FoodListAdapter, fragment: FoodFragment) {
        adapter.setFoodDetailOnClickListener(object: FoodDetailOnClickListener {
            override fun onClick(food: Food) {
                val action = FoodFragmentDirections.actionFoodFragmentToFoodDetailFragment(food =  food)
                fragment.findNavController().navigate(action)
            }
        })
    }
}