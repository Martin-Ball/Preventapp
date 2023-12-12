package com.martin.preventapp.view.activities.delivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.ProfileController
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.ActivityMainDeliveryBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.view.entities.Permission
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveredFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveryFragment
import com.martin.preventapp.view.fragments.seller.create.CreateOrderFragment
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
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Ubicacion del cliente", ignoreCase = true) ||
                                it.name.equals("Rechazar Pedido", ignoreCase = true) ||
                                it.name.equals("Ver pedidos", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        OrdersDeliveredFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)
                        OrdersToDeliverController.instance!!.setContext(this)
                        showFragment(OrdersDeliveryFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }

                    true
                }
                R.id.navigation_profile -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    ProfileController.instance!!.setContext(this)
                    showFragment(ProfileFragment.instance!!)
                    true
                }
                else -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Ubicacion del cliente", ignoreCase = true) ||
                                it.name.equals("Rechazar Pedido", ignoreCase = true) ||
                                it.name.equals("Ver pedidos", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        OrdersDeliveredFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)
                        OrdersToDeliverController.instance!!.setContext(this)
                        showFragment(OrdersDeliveryFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }

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