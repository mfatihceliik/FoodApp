package com.muhammedfatihcelik.foodorder.utils.listener

import com.muhammedfatihcelik.foodorder.data.entity.food.Food

interface FoodDetailOnClickListener {
    fun onClick(food: Food)
}