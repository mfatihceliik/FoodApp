package com.muhammedfatihcelik.foodorder.ui.login

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.muhammedfatihcelik.foodorder.R
import com.muhammedfatihcelik.foodorder.databinding.FragmentLoginBinding
import com.muhammedfatihcelik.foodorder.utils.*
import kotlin.math.log

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(email = email, password = password).observe(viewLifecycleOwner) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {

                        it.data?.let { loginResponse ->
                            viewModel.setUser(loginResponse.data)
                        }
                        binding.progressBar.gone()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    Resource.Status.ERROR -> {
                        binding.progressBar.hide()
                        //val alertDialog = AlertDialog.Builder(context)
                        //alertDialog.set(it.message)
                    }
                }
            }
        }

    }
}