package com.martin.preventapp.view.activities.delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.databinding.ActivityMainDeliveryBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.view.entities.Permission
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveredFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveryFragment
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment

class MainDeliveryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDeliveryBinding
    private var permissions = listOf<Permission>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainDeliveryBinding.inflate(layoutInflater);
        setContentView(binding.root)

        permissions = Application.getPermissionsUserShared(this) ?: emptyList()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_orders -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder = permissions.find { it.name == "Enviar Pedido" }

                    if(permissionToSendOrder?.isEnabled != false){
                        OrdersDeliveredFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)
                        OrdersToDeliverController.instance!!.setContext(this)
                        showFragment(OrdersDeliveryFragment.instance!!)
                    }else{
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE
                    }

                    true
                }
                R.id.navigation_profile -> {
                    showFragment(ProfileFragment.instance!!)
                    true
                }
                else -> {
                    showFragment(ProfileFragment.instance!!)
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

    private fun removeCurrentFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.main_container)

        if (currentFragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(currentFragment)
                .commit()
        }
    }
}