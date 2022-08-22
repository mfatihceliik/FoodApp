package com.muhammedfatihcelik.foodorder.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressDto
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressDtoResponse
import com.muhammedfatihcelik.foodorder.data.entity.common.User
import com.muhammedfatihcelik.foodorder.databinding.FragmentProfileBinding
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.gone
import com.muhammedfatihcelik.foodorder.utils.hide
import com.muhammedfatihcelik.foodorder.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProfileDetail()
        go()
        observeCurrentAddress()
    }

    private fun go() {
        binding.editProfile.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToAddressFragment()
            this.findNavController().navigate(action)
        }
    }

    private fun observeCurrentAddress() {
        viewModel.getByCurrentAddress().observe(viewLifecycleOwner) {
            when(it.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { addressDtoResponse ->
                        Log.v("PROFILEFRAGMENT",addressDtoResponse.toString())
                        setCurrentAddressDetail(addressDto = addressDtoResponse.data)
                    }
                }
                Resource.Status.ERROR -> binding.progressBar.gone()
            }
        }
    }

    private fun setCurrentAddressDetail(addressDto: AddressDto) {
        binding.addressTitleEditText.isEnabled = false
        binding.addressTitleEditText.setText(addressDto.addressTitle)
        binding.cityEditText.setText(addressDto.city)
        binding.districtEditText.setText(addressDto.district)
        binding.openAddressEditText.setText(addressDto.openAddress)
    }

    private fun setProfileDetail() {
        var user: User? = viewModel.getUserDetail()
        user?.let {
            binding.userNameTextView.text = it.firstName + " " + it.lastName
            binding.emailTextView.text = it.email
        }
    }
}
