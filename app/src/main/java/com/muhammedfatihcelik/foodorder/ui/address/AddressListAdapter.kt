package com.muhammedfatihcelik.foodorder.ui.address

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammedfatihcelik.foodorder.data.entity.address.Address
import com.muhammedfatihcelik.foodorder.databinding.ItemAddressBinding
import com.muhammedfatihcelik.foodorder.utils.listener.AddressChangeOnClickListener
import com.muhammedfatihcelik.foodorder.utils.listener.AddressEditOnClickListener

class AddressListAdapter: RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder>() {

    private val addressList: ArrayList<Address> = ArrayList()
    private var addressEditOnClickListener: AddressEditOnClickListener? = null
    private var addressChangeOnClickListener: AddressChangeOnClickListener? = null

    class AddressListViewHolder(private var binding: ItemAddressBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address, addressEditOnClickListener: AddressEditOnClickListener?, addressChangeOnClickListener: AddressChangeOnClickListener?) {
            binding.addressTitleTextView.text = address.addressTitle
            binding.openAddressTextView.text = address.openAddress
            binding.editImageView.setOnClickListener {
                addressEditOnClickListener?.onClick(address = address)
            }
            binding.cardView.setOnClickListener {
                addressChangeOnClickListener?.onClick(address = address)
                //binding.cardView.foreground = ContextCompat.getDrawable(binding.root.context, R.drawable.custom_card_view_border)
                //binding.cardView.foreground = null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressListViewHolder {
        val binding = ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressListViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: AddressListViewHolder, position: Int) {
        val addressList = this.addressList[position]
        holder.bind(address = addressList, addressEditOnClickListener = addressEditOnClickListener, addressChangeOnClickListener = addressChangeOnClickListener)
    }

    override fun getItemCount(): Int = addressList.size

    fun setAddressList(addressList: List<Address>) {
        this.addressList.clear()
        this.addressList.addAll(addressList)
        notifyDataSetChanged()
    }

    fun setAddressEditOnClickListener(addressEditOnClickListener: AddressEditOnClickListener) {
        this.addressEditOnClickListener = addressEditOnClickListener
    }

    fun setAddressChangeOnClickListener(addressChangeOnClickListener: AddressChangeOnClickListener){
        this.addressChangeOnClickListener = addressChangeOnClickListener
    }
}