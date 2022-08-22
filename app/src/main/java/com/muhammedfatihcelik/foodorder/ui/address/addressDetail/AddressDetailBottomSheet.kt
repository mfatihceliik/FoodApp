package com.muhammedfatihcelik.foodorder.ui.address.addressDetail

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.data.entity.address.AddressRequest
import com.muhammedfatihcelik.foodorder.data.entity.address.City
import com.muhammedfatihcelik.foodorder.data.entity.address.District
import com.muhammedfatihcelik.foodorder.databinding.BottomSheetAddAddressBinding
import com.muhammedfatihcelik.foodorder.utils.Resource
import com.muhammedfatihcelik.foodorder.utils.gone
import com.muhammedfatihcelik.foodorder.utils.hide
import com.muhammedfatihcelik.foodorder.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressDetailBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetAddAddressBinding
    val viewModel: AddressDetailViewModel by viewModels()
    private val navArgs: AddressDetailBottomSheetArgs by navArgs()
    private lateinit var cityList: List<City>
    private lateinit var districtList: List<District>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetAddAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setArguments()
        observeCities()
        onItemChangedListener()
        addNewAddressOnClickListener()
        updateAddressClickListener()
    }

    private fun setArguments() {
        navArgs.address?.let {
            binding.openAddressEditText.setText(it.openAddress)
            binding.addressTitleTextView.setText(it.addressTitle)
            binding.addNewAddressButton.setText("Update address")
        }
    }

    private fun getAddress(): Address {
        val addressTitle = binding.addressTitleTextView.text.toString()
        val openAddress = binding.openAddressEditText.text.toString()
        val userId = viewModel.getUser()!!.id
        val districtId = districtList[binding.districtSpinner.selectedItemPosition].id
        val id = navArgs.address?.id ?: 0

        return Address(id, userId, districtId, addressTitle, openAddress, true)
    }

    private fun updateAddressClickListener() {
        binding.addNewAddressButton.setOnClickListener {
            if(binding.addNewAddressButton.text.toString() == "Update address"){
                observeUpdateAddress(getAddress())
            }
        }
    }

    private fun observeUpdateAddress(address: Address) {
        viewModel.updateUserAddress(address = address).observe(viewLifecycleOwner) {
            when(it.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { baseResponse ->
                        Toast.makeText(context, baseResponse.message, Toast.LENGTH_LONG).show()
                        this.findNavController().popBackStack()
                    }
                }
                Resource.Status.ERROR -> binding.progressBar.gone()
            }
        }
    }

    private fun addNewAddressOnClickListener() {
        binding.addNewAddressButton.setOnClickListener {
            if(binding.addNewAddressButton.text == "Add new address"){
                observeAddNewAddress(getAddress())
            }
        }
    }

    private fun observeAddNewAddress(address: Address) {
        viewModel.addNewAddress(address = address).observe(viewLifecycleOwner) {
            when(it.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    Toast.makeText(context, it.data?.message, Toast.LENGTH_LONG).show()
                    this.findNavController().popBackStack()
                }
                Resource.Status.ERROR -> binding.progressBar.show()
            }
        }
    }

    private fun observeCities() {
        viewModel.getCities().observe(viewLifecycleOwner) {
            when(it.status){
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    it.data?.let { cityResponse ->
                        cityList = cityResponse.data

                        val cityNameList = ArrayList<String>()
                        cityList.forEach { city ->
                            cityNameList.add(city.city)
                        }
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_expandable_list_item_1, cityNameList)
                        binding.citySpinner.adapter = arrayAdapter
                    }
                }
                Resource.Status.ERROR -> binding.progressBar.gone()
            }
        }
    }

    private fun observeDistricts(cityId: Int) {
        viewModel.getDistrictsByCityId(cityId = cityId).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    binding.progressBar.hide()

                    it.data?.let { districtResponse ->

                        districtList = districtResponse.data

                        val districtNameList = ArrayList<String>()
                        districtList.forEach { district ->
                            districtNameList.add(district.district)
                        }
                        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_expandable_list_item_1, districtNameList)
                        binding.districtSpinner.adapter = arrayAdapter
                    }
                }
                Resource.Status.ERROR -> binding.progressBar.gone()
            }
        }
    }

    private fun onItemChangedListener() {
        binding.citySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val cityId = cityList[p2].id
                observeDistricts(cityId = cityId)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}