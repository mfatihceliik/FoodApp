package com.muhammedfatihcelik.foodorder.ui.basket

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhammedfatihcelik.foodorder.data.entity.basket.Basket
import com.muhammedfatihcelik.foodorder.databinding.ItemBasketFoodsBinding

class BasketFoodListAdapter: RecyclerView.Adapter<BasketFoodListAdapter.BasketFoodAdapter>() {

    private val basketList: ArrayList<Basket> = ArrayList()

    class BasketFoodAdapter(private val binding: ItemBasketFoodsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(basket: Basket, context: Context){
            binding.foodTitle.text = basket.title
            binding.foodIngredients.text = basket.ingredients
            binding.foodQuantity.text = basket.quantity.toString()

            Glide.with(context).load(basket.foodImagePath).into(binding.foodImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketFoodAdapter {
        val binding = ItemBasketFoodsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketFoodAdapter(binding)
    }

    override fun onBindViewHolder(holder: BasketFoodAdapter, position: Int) {
        val basketList = this.basketList[position]
        holder.bind(basket = basketList, context = holder.itemView.context)
    }

    override fun getItemCount(): Int = this.basketList.size

    fun setBasketList(basketList: List<Basket>) {
        this.basketList.clear()
        this.basketList.addAll(basketList)
        notifyDataSetChanged()
    }
}