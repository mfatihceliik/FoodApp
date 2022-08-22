package com.muhammedfatihcelik.foodorder.ui.food.foodDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.muhammedfatihcelik.foodorder.databinding.ItemFoodDetailBottomsheetBinding
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.gone
import com.muhammedfatihcelik.foodorder.utils.hide
import com.muhammedfatihcelik.foodorder.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: ItemFoodDetailBottomsheetBinding
    private val viewModel: FoodDetailViewModel by viewModels()
    private val args: FoodDetailBottomSheetArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemFoodDetailBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFoodDetails()
        increaseFoodQuantity()
        decreaseFoodQuantity()
        foodAddToBasket()
    }

    private fun setFoodDetails() {
        binding.foodName.text = args.food.title
        binding.foodIngredientsTextView.text = args.food.ingredients
        binding.foodPriceTextView.text = args.food.price.toString()
        binding.foodQuantity.text = args.food.quantity.toString()
        context?.let { context ->
            Glide.with(context).load(args.food.imagePath).into(binding.foodImageView)
        }
    }

    private fun increaseFoodQuantity() {
        binding.increaseFood.setOnClickListener {
            binding.foodQuantity.text = viewModel.increaseFoodQuantity(args.food)
        }
    }
    private fun decreaseFoodQuantity() {
        binding.decreaseFood.setOnClickListener {
            binding.foodQuantity.text = viewModel.decreaseFoodQuantity(args.food)
        }
    }

    private fun foodAddToBasket() {
        binding.addToBasket.setOnClickListener {
            viewModel.foodAddToBasket(args.food).observe(viewLifecycleOwner) {
                when(it.status){
                    Resource.Status.LOADING -> {
                        binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.hide()
                        Toast.makeText(context, args.food.title + " " + it.data?.message.toString(), Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    Resource.Status.ERROR -> {
                        binding.progressBar.gone()
                    }
                }
            }
        }
    }
}


















