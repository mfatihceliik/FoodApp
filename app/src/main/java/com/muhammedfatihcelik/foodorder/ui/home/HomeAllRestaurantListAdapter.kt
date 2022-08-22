package com.muhammedfatihcelik.foodorder.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhammedfatihcelik.foodorder.data.entity.restaurants.Restaurant
import com.muhammedfatihcelik.foodorder.databinding.ItemAllRestaurantsBinding
import com.muhammedfatihcelik.foodorder.utils.listener.RestaurantOnClickListener
import com.muhammedfatihcelik.foodorder.utils.setRestaurantPoint

class HomeAllRestaurantListAdapter: RecyclerView.Adapter<HomeAllRestaurantListAdapter.HomeViewHolder>() {

    private val restaurantList : ArrayList<Restaurant> = ArrayList()
    private var listener: RestaurantOnClickListener? = null

    class HomeViewHolder(private val binding: ItemAllRestaurantsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant, listener: RestaurantOnClickListener) {
            binding.restaurantBrandTextView.text = restaurant.brand
            binding.restaurantDistrictTextView.text = restaurant.district
            binding.restaurantPointTextView.text = binding.restaurantPointTextView.setRestaurantPoint(restaurantPoint = restaurant.point)
            Glide.with(this@HomeViewHolder.itemView.context).load(restaurant.imagePath).into(binding.restaurantImageView)
            binding.cardView.setOnClickListener {
                listener.onClick(restaurant = restaurant)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemAllRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val restaurantList = restaurantList[position]
        this.listener?.let { listener ->
            holder.bind(restaurant = restaurantList, listener = listener)
        }
    }

    override fun getItemCount(): Int = restaurantList.size

    fun setRestaurantOnClickListener(listener: RestaurantOnClickListener) {
        this.listener = listener
    }

    fun setRestaurantList(restaurantList: List<Restaurant>) {
        this.restaurantList.clear()
        this.restaurantList.addAll(restaurantList)
        notifyDataSetChanged()
    }
}