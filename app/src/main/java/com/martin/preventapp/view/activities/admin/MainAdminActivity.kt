package com.martin.preventapp.view.activities.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.databinding.ActivityMainAdminBinding
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment
import com.martin.preventapp.view.fragments.seller.create.CreateOrderFragment
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedFragment

class MainAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainAdminBinding.inflate(layoutInflater);
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