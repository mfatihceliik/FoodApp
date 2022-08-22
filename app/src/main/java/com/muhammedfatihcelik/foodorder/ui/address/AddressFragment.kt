package com.muhammedfatihcelik.foodorder.ui.address

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammedfatihcelik.foodorder.R
import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressRequest
import com.muhammedfatihcelik.foodorder.databinding.FragmentAddressBinding
import com.muhammedfatihcelik.foodorder.utils.*
import com.muhammedfatihcelik.foodorder.utils.listener.AddressChangeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.ticker

@AndroidEntryPoint
class AddressFragment: Fragment() {

    private lateinit var binding: FragmentAddressBinding
    private val viewModel: AddressViewModel by viewModels()
    private var addressAdapter: AddressListAdapter = AddressListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeUserAddress()
        navigateAddressAddBottomSheetFragment()
        actionToAddressAddBottomSheetFragmentNull()
        changeAddressOnClickListener()
    }

    private fun initAdapter() {
        binding.addressListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.addressListRecyclerView.adapter = addressAdapter
    }
    private fun actionToAddressAddBottomSheetFragmentNull () {
        binding.addNewAddressButton.setOnClickListener {
            viewModel.actionToAddressAddBottomSheetFragmentNull(fragment = this)
        }
    }
    private fun navigateAddressAddBottomSheetFragment() {
        viewModel.actionToAddressAddBottomSheetFragment(adapter = addressAdapter, this)
    }

    private fun observeUserAddress() {
        viewModel.getAddressList().observe(viewLifecycleOwner) {
            when(it.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { addressResponse ->
                        addressAdapter.setAddressList(addressList = addressResponse.data)
                    }
                }
                Resource.Status.ERROR -> binding.progressBar.gone()
            }
        }
    }

    private fun changeAddressOnClickListener() {
        addressAdapter.setAddressChangeOnClickListener(object: AddressChangeOnClickListener {
            override fun onClick(address: Address) {
                val alertDialog = AlertDialog.Builder(context)

                alertDialog
                    .setTitle("Update address")
                    .setMessage("Do you want to change your addresss ?")
                    .setIcon(R.drawable.ic_home)
                    .setCancelable(true)
                    .setNegativeButton("No") { dialogInterface, i ->  dialogInterface.dismiss()}
                    .setPositiveButton("Yes") { dialogInterface, i ->
                        observeUpdateAddress(address = address)
                        dialogInterface.dismiss()
                    }
                    .show()
            }
        })
    }

    fun observeUpdateAddress(address: Address) {
        viewModel.updateUserAddress(address = address).observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { baseResponse ->

                        Toast.makeText(context, baseResponse.message, Toast.LENGTH_SHORT).show()
                    }
                }
                Resource.Status.ERROR -> binding.progressBar.hide()
            }
        }
    }
}