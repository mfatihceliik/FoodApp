package com.muhammedfatihcelik.foodorder.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhammedfatihcelik.foodorder.data.entity.restaurants.Restaurant
import com.muhammedfatihcelik.foodorder.databinding.ItemBestRestaurantsBinding
import com.muhammedfatihcelik.foodorder.utils.listener.RestaurantOnClickListener
import com.muhammedfatihcelik.foodorder.utils.setRestaurantPoint

class HomeBestRestaurantListAdapter: RecyclerView.Adapter<HomeBestRestaurantListAdapter.HomeViewHolder>() {

    private val restaurantList: ArrayList<Restaurant> = ArrayList()
    private var listener: RestaurantOnClickListener? = null

    class HomeViewHolder(private val binding: ItemBestRestaurantsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant, listener: RestaurantOnClickListener ,context: Context) {
            binding.brandTextView.text = restaurant.brand
            binding.pointTextView.text = binding.pointTextView.setRestaurantPoint(restaurantPoint = restaurant.point)
            Glide.with(context).load(restaurant.imagePath).into(binding.restaurantImageView)
            binding.linearLayout1.setOnClickListener {
                listener.onClick(restaurant = restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemBestRestaurantsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = restaurantList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val restaurantList = this.restaurantList[position]
        this.listener?.let { listener -> holder.bind(restaurant = restaurantList, listener =  listener, context = holder.itemView.context) }
    }

    fun setRestaurantOnClickListener(listener: RestaurantOnClickListener) {
        this.listener = listener
    }

    fun setRestaurantList(restaurantList: List<Restaurant>) {
        this.restaurantList.clear()
        this.restaurantList.addAll(restaurantList)
        notifyDataSetChanged()
    }

}