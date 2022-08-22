package com.muhammedfatihcelik.foodorder.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.muhammedfatihcelik.foodorder.data.ApiRepository
import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressRequest
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressResponse
import com.muhammedfatihcelik.foodorder.data.entity.common.BaseResponse
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.listener.AddressChangeOnClickListener
import com.muhammedfatihcelik.foodorder.utils.listener.AddressEditOnClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    val apiRepository: ApiRepository,
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    fun getAddressList(): LiveData<Resource<AddressResponse>> {
        return apiRepository.getAddressByUserId()
    }

    fun updateUserAddress(address: Address): LiveData<Resource<BaseResponse>> {
        return apiRepository.updateUserAddress(address)
    }

    fun actionToAddressAddBottomSheetFragment(adapter: AddressListAdapter, fragment: AddressFragment) {
        adapter.setAddressEditOnClickListener(object: AddressEditOnClickListener {
            override fun onClick(address: Address) {
                val action = AddressFragmentDirections.actionAddressFragmentToAddressAddBottomSheet(address = address, updateButton = true)
                fragment.findNavController().navigate(action)
            }
        })
    }

    fun actionToAddressAddBottomSheetFragmentNull(fragment: AddressFragment) {
        val action = AddressFragmentDirections.actionAddressFragmentToAddressAddBottomSheet(address = null)
        fragment.findNavController().navigate(action)
    }
}