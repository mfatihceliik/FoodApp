package com.muhammedfatihcelik.foodorder.ui.basket

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.muhammedfatihcelik.foodorder.R
import com.muhammedfatihcelik.foodorder.data.entity.basket.BasketResponse
import com.muhammedfatihcelik.foodorder.databinding.FragmentBasketBinding
import com.muhammedfatihcelik.foodorder.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class BasketFragment : Fragment() {
    
    private lateinit var binding: FragmentBasketBinding
    private val viewModel: BasketViewModel by viewModels()
    private val basketFoodListAdapter: BasketFoodListAdapter = BasketFoodListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeBasketListData()
        observeCompleteOrder()
    }

    private fun initAdapter() {
        binding.basketFoodListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.basketFoodListRecyclerView.adapter = basketFoodListAdapter
        /*val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.bestRestaurantsRecyclerView)*/
    }

    private fun calculateTotalPrice(totalPrice: String) {
        binding.totalPriceTextView.text = totalPrice
    }
    private fun clearBasket() {
        binding.basketFoodListRecyclerView.visibility = View.INVISIBLE
        binding.emptyBasketTextView.visibility = View.VISIBLE
    }

    private fun observeCompleteOrder() {
        binding.completeOrderButton.setOnClickListener {
            viewModel.completeOrder().observe(viewLifecycleOwner) {
                when(it.status){
                    Resource.Status.LOADING -> {
                        binding.ProgressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        binding.ProgressBar.hide()
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.set("Success" , it.data?.message.toString(), R.drawable.ic_success)
                        clearBasket()
                        basketFoodListAdapter.setBasketList(emptyList())
                    }
                    Resource.Status.ERROR -> {
                        binding.ProgressBar.gone()
                    }
                }
            }
        }
    }

    private fun observeBasketListData() {
        viewModel.getBasketByUserId().observe(viewLifecycleOwner) {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.ProgressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.ProgressBar.hide()
                    it.data?.let { basketResponse ->
                        basketFoodListAdapter.setBasketList(basketResponse.data)
                        calculateTotalPrice(viewModel.calculateTotalPrice(basketList = basketResponse.data))
                    }
                }
                Resource.Status.ERROR -> {
                    binding.ProgressBar.gone()
                }
            }
        }
    }
}