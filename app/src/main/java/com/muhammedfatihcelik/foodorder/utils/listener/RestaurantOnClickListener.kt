package com.muhammedfatihcelik.foodorder.utils.listener

import com.muhammedfatihcelik.foodorder.data.entity.restaurants.Restaurant

interface RestaurantOnClickListener {
    fun onClick(restaurant: Restaurant)
}