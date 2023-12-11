package com.martin.preventapp.view.activities.seller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.ProfileController
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.view.fragments.seller.create.CreateOrderFragment
import com.martin.preventapp.databinding.ActivityMainBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.view.entities.Permission
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedFragment

class MainSellerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var permissions = listOf<Permission>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        permissions = Application.getPermissionsUserShared(this) ?: emptyList()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
           when (menuItem.itemId) {
                R.id.navigation_list -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Enviar pedido", ignoreCase = true) ||
                                it.name.equals("Crear pedido", ignoreCase = true) ||
                                it.name.equals("Agregar nota", ignoreCase = true) ||
                                it.name.equals("Cargar productos", ignoreCase = true) ||
                                it.name.equals("Asignar cantidades", ignoreCase = true) ||
                                it.name.equals("Seleccionar cliente", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        CreateOrderController.instance!!.setContext(this)
                        showFragment(CreateOrderFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }

                    true
                }
                R.id.navigation_recommended -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Recomendar productos", ignoreCase = true) ||
                                it.name.equals("Generar reporte", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        RecommendedController.instance!!.setContext(this)
                        showFragment(RecommendedFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }

                    true
                }
                R.id.navigation_orders -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Ver pedidos", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        OrdersFragment.instance!!.setListener(OrdersController.instance!!)
                        ConfirmedOrdersController.instance!!.setContext(this)
                        showFragment(OrdersFragment.instance!!)
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
                        it.name.equals("Enviar pedido", ignoreCase = true) ||
                                it.name.equals("Crear pedido", ignoreCase = true) ||
                                it.name.equals("Agregar nota", ignoreCase = true) ||
                                it.name.equals("Cargar productos", ignoreCase = true) ||
                                it.name.equals("Asignar cantidades", ignoreCase = true) ||
                                it.name.equals("Seleccionar cliente", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        CreateOrderController.instance!!.setContext(this)
                        showFragment(CreateOrderFragment.instance!!)
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

        binding.bottomNavigationView.selectedItemId = R.id.navigation_list
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