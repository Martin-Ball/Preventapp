package com.martin.preventapp.view.activities.seller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.view.fragments.seller.create.CreateOrderFragment
import com.martin.preventapp.databinding.ActivityMainBinding
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedFragment

class MainSellerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
           when (menuItem.itemId) {
                R.id.navigation_list -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
                R.id.navigation_recommended -> {
                    showFragment(RecommendedFragment.instance!!)
                    true
                }
                R.id.navigation_orders -> {
                    OrdersFragment.instance!!.setListener(OrdersController.instance!!)
                    showFragment(OrdersFragment.instance!!)
                    true
                }
                R.id.navigation_profile -> {
                    showFragment(ProfileFragment.instance!!)
                    true
                }
                else -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.navigation_list
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}