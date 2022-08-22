package com.muhammedfatihcelik.foodorder.ui.food

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhammedfatihcelik.foodorder.data.entity.food.Food
import com.muhammedfatihcelik.foodorder.databinding.ItemFoodBinding
import com.muhammedfatihcelik.foodorder.utils.listener.FoodDetailOnClickListener
import com.muhammedfatihcelik.foodorder.utils.listener.FoodItemClickListener

class FoodListAdapter: RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {

    private val foodList: ArrayList<Food> = ArrayList()
    private var foodDetailOnClickListener: FoodDetailOnClickListener? = null
    private var foodItemClickListener: FoodItemClickListener? = null

    class FoodViewHolder(private val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food, context: Context, foodDetailOnClickListener: FoodDetailOnClickListener?, foodItemClickListener: FoodItemClickListener?) {

            binding.foodTitleTextView.text = food.title
            binding.foodPriceTextView.text = "${food.price} TL"
            binding.foodIngredientsTextView.text = food.ingredients
            Glide.with(context).load(food.imagePath).into(binding.foodImageView)

            binding.foodCardView.setOnClickListener {
                foodDetailOnClickListener?.onClick(food = food)
            }

            /*foodAddToBasketClickListener(food, foodItemClickListener)
            increaseFoodClickListener(food, foodItemClickListener)
            decreaseFoodClickListener(food, foodItemClickListener)*/
        }
        /*private fun foodAddToBasketClickListener(food: Food, foodItemClickListener: FoodItemClickListener?) {
            binding.foodAddToBasketTextView.setOnClickListener {
                foodItemClickListener?.increase(food)
                binding.foodAddToBasketTextView.visibility = View.INVISIBLE
                binding.IncreaseAndDecreaseFoodCardView.visibility = View.VISIBLE
                binding.foodCount.text = food.quantity.toString()
            }
        }
        private fun increaseFoodClickListener(food: Food, foodItemClickListener: FoodItemClickListener?){
            binding.increaseFood.setOnClickListener {
                foodItemClickListener?.increase(food)
                binding.foodCount.text = food.quantity.toString()
            }
        }
        private fun decreaseFoodClickListener(food: Food, foodItemClickListener: FoodItemClickListener?) {
            binding.decreaseFood.setOnClickListener {
                foodItemClickListener?.decrease(food).also {
                    if(it == 0){
                        binding.foodAddToBasketTextView.visibility = View.VISIBLE
                        binding.IncreaseAndDecreaseFoodCardView.visibility = View.INVISIBLE
                    }
                }
                binding.foodCount.text = food.quantity.toString()
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodList = this.foodList[position]
        holder.bind(food = foodList, context = holder.itemView.context, foodDetailOnClickListener = foodDetailOnClickListener, foodItemClickListener =  foodItemClickListener)
    }

    override fun getItemCount(): Int = foodList.size

    fun setFoodData(foodList: List<Food>) {
        this.foodList.clear()
        this.foodList.addAll(foodList)
        notifyDataSetChanged()
    }

    fun setFoodDetailOnClickListener(foodDetailOnClickListener: FoodDetailOnClickListener){
        this.foodDetailOnClickListener = foodDetailOnClickListener
    }

    fun setFoodItemClickListener(foodItemClickListener: FoodItemClickListener?) {
        this.foodItemClickListener = foodItemClickListener
    }
}