package com.muhammedfatihcelik.foodorder.ui.home

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.restaurants.Restaurant
import com.muhammedfatihcelik.foodorder.data.entity.restaurants.RestaurantResponse
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.listener.RestaurantOnClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getAllRestaurants(): LiveData<Resource<RestaurantResponse>> {
        return apiRepository.getAllRestaurants()
    }

    fun getRestaurantByPoint(): LiveData<Resource<RestaurantResponse>> {
        return apiRepository.getRestaurantByPoint()
    }

    fun goFoodDetail(adapter: HomeBestRestaurantListAdapter, fragment: HomeFragment) {
        adapter.setRestaurantOnClickListener(object: RestaurantOnClickListener {
            override fun onClick(restaurant: Restaurant) {
                val action = HomeFragmentDirections.actionHomeFragmentToFoodFragment(restaurant = restaurant)
                fragment.findNavController().navigate(action)
            }
        })
    }

    fun goFoodDetail(adapter: HomeAllRestaurantListAdapter, fragment: HomeFragment){
        adapter.setRestaurantOnClickListener(object: RestaurantOnClickListener {
            override fun onClick(restaurant: Restaurant) {
                val action = HomeFragmentDirections.actionHomeFragmentToFoodFragment(restaurant = restaurant)
                fragment.findNavController().navigate(action)
            }
        })
    }
}