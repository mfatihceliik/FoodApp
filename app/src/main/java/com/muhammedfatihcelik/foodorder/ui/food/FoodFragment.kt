package com.muhammedfatihcelik.foodorder.ui.food
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.muhammedfatihcelik.foodorder.data.entity.food.Food
import com.muhammedfatihcelik.foodorder.databinding.FragmentFoodBinding
import com.muhammedfatihcelik.foodorder.utils.*
import com.muhammedfatihcelik.foodorder.utils.listener.FoodItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodFragment: Fragment() {

    private lateinit var binding: FragmentFoodBinding
    private val viewModel: FoodViewModel by viewModels()
    private val adapter: FoodListAdapter = FoodListAdapter()
    private val navArgs: FoodFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setRestaurantDetails()
        observeFetchFoodsData()
        observeFoodQuantity()
        goFoodDetailFragment()
    }

    private fun goFoodDetailFragment() {
        viewModel.goFoodDetailFragment(adapter = adapter, fragment = this@FoodFragment)
    }

    private fun initRecyclerView() {
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.foodRecyclerView.adapter = adapter
    }
    private fun setRestaurantDetails() {
        binding.brandTextView.text = navArgs.restaurant.brand
        binding.pointTextView.text = binding.pointTextView.setRestaurantPoint(restaurantPoint = navArgs.restaurant.point)
        context?.let {
            Glide.with(it).load(navArgs.restaurant.imagePath).into(binding.restaurantImageView)
        }
    }
    private fun observeFetchFoodsData() {
        viewModel.getFoodsByRestaurantId(navArgs.restaurant.id).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.ProgressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.ProgressBar.hide()
                    it.data?.let { foodResponse ->
                        adapter.setFoodData(foodResponse.data)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.ProgressBar.gone()
                }
            }
        }
    }
    private fun observeFoodQuantity() {
        viewModel.foodQuantity.observe(viewLifecycleOwner) {
            adapter.setFoodItemClickListener(object: FoodItemClickListener{

                override fun increase(food: Food): Int {
                    return viewModel.foodQuantityIncrease(food)
                }

                override fun decrease(food: Food): Int {
                    return viewModel.foodQuantityDecrease(food)
                }
            })
        }
    }
}