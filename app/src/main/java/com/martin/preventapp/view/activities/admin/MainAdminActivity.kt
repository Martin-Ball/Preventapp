package com.martin.preventapp.view.activities.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.databinding.ActivityMainAdminBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.view.entities.Permission
import com.martin.preventapp.view.fragments.admin.list.ListFragment
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment
import com.martin.preventapp.view.fragments.admin.users.UserFragment
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment

class MainAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainAdminBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_orders -> {
                    ConfirmedOrdersFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)

                    showFragment(OrdersAdminFragment.instance!!)
                    true
                }
                R.id.navigation_list -> {
                    ListController.instance!!.setContext(this)
                    showFragment(ListFragment.instance!!)
                    true
                }
                R.id.navigation_users -> {
                    showFragment(UserFragment.instance!!)
                    true
                }
                R.id.navigation_profile -> {
                    showFragment(ProfileFragment.instance!!)
                    true
                }
                else -> {
                    ConfirmedOrdersFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)
                    showFragment(OrdersAdminFragment.instance!!)
                    true
                }
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.navigation_orders
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}