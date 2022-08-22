package com.muhammedfatihcelik.foodorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.muhammedfatihcelik.foodorder.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.let {
            it.bottomNavigationView

            Handler().postDelayed({
                it.bottomNavigationView.visibility = View.VISIBLE
                setupBottomNavMenu(it, findNavController(R.id.nav_host_fragment))
            }, 1500)
        }

    }

    fun setupBottomNavMenu(activityMainBinding: ActivityMainBinding, navController: NavController) {
        activityMainBinding.bottomNavigationView.setupWithNavController(navController = navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val action = item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
        return action || super.onOptionsItemSelected(item)
    }
}