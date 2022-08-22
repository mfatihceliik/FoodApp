package com.muhammedfatihcelik.foodorder.utils.listener

import com.muhammedfatihcelik.foodorder.data.entity.food.Food

interface FoodItemClickListener {
    fun increase(food: Food): Int
    fun decrease(food: Food): Int
}