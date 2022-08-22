package com.muhammedfatihcelik.foodorder.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.muhammedfatihcelik.foodorder.R
import com.muhammedfatihcelik.foodorder.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkTokenAndNavigate()

        viewModel.observeLiveData().observe(viewLifecycleOwner, Observer {
            when(it) {
                "HOME" -> {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
                "LOGIN" -> {
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        })

    }
}