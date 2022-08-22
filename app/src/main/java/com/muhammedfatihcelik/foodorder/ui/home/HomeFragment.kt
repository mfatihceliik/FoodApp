package com.muhammedfatihcelik.foodorder.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.muhammedfatihcelik.foodorder.databinding.FragmentHomeBinding
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.gone
import com.muhammedfatihcelik.foodorder.utils.hide
import com.muhammedfatihcelik.foodorder.utils.show
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.RecyclerView.SmoothScroller


@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapterBestRestaurant: HomeBestRestaurantListAdapter = HomeBestRestaurantListAdapter()
    private val adapterAllRestaurant: HomeAllRestaurantListAdapter = HomeAllRestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        goFoodDetail()
        observeAllRestaurantData()
        observeBestRestaurantData()
    }

    private fun init() {

        binding.bestRestaurantsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.bestRestaurantsRecyclerView.adapter = adapterBestRestaurant
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.bestRestaurantsRecyclerView)

        binding.allRestaurantsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.allRestaurantsRecyclerView.adapter = adapterAllRestaurant
    }
    private fun goFoodDetail() {
        viewModel.goFoodDetail(adapter = adapterBestRestaurant, fragment = this)
        viewModel.goFoodDetail(adapter = adapterAllRestaurant, fragment = this)
    }
    private fun observeAllRestaurantData() {
        viewModel.getAllRestaurants().observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { restaurantResponse ->
                        adapterAllRestaurant.setRestaurantList(restaurantList = restaurantResponse.data)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                }
            }
        }
    }

    private fun observeBestRestaurantData() {
        viewModel.getRestaurantByPoint().observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { restaurantResponse ->
                        adapterBestRestaurant.setRestaurantList(restaurantList = restaurantResponse.data)
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.gone()
                }
            }
        }
    }
}