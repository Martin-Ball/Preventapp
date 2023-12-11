package com.martin.preventapp.view.activities.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.ProfileController
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.databinding.ActivityMainAdminBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.view.entities.Permission
import com.martin.preventapp.view.fragments.admin.list.ListFragment
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment
import com.martin.preventapp.view.fragments.admin.users.UserFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveredFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveryFragment
import com.martin.preventapp.view.fragments.seller.profile.ProfileFragment

class MainAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainAdminBinding
    private var permissions = listOf<Permission>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainAdminBinding.inflate(layoutInflater);
        setContentView(binding.root)
        permissions = Application.getPermissionsUserShared(this) ?: emptyList()

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_orders -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Recibir pedido", ignoreCase = true) ||
                                it.name.equals("Enviar a reparto", ignoreCase = true) ||
                                it.name.equals("Seleccion de pedido", ignoreCase = true) ||
                                it.name.equals("Confirmar pedido", ignoreCase = true) ||
                                it.name.equals("Exportar excel", ignoreCase = true) ||
                                it.name.equals("Entregar Pedido", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        ConfirmedOrdersFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)
                        NewOrdersController.instance!!.setContext(this)
                        showFragment(OrdersAdminFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }
                    true
                }
                R.id.navigation_list -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Agregar lista de precio", ignoreCase = true) ||
                                it.name.equals("Agregar lista de clientes", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        ListController.instance!!.setContext(this)
                        showFragment(ListFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }

                    true
                }
                R.id.navigation_users -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Gestionar usuarios", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        showFragment(UserFragment.instance!!)
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
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Crear backup", ignoreCase = true) ||
                                it.name.equals("Restaurar Backup", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        ProfileController.instance!!.setContext(this)
                        showFragment(ProfileFragment.instance!!)
                    } else {
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE

                        val disabledPermissions = permissionToSendOrder.filter { !it.isEnabled }
                        val disabledPermissionsText = disabledPermissions.joinToString { it.name }

                        binding.tvEnabledAction.text = "Los siguientes permisos están deshabilitados: $disabledPermissionsText"
                    }

                    true
                }
                else -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder : List<Permission> = permissions.filter {
                        it.name.equals("Recibir pedido", ignoreCase = true) ||
                                it.name.equals("Enviar a reparto", ignoreCase = true) ||
                                it.name.equals("Seleccion de pedido", ignoreCase = true) ||
                                it.name.equals("Confirmar pedido", ignoreCase = true) ||
                                it.name.equals("Exportar excel", ignoreCase = true) ||
                                it.name.equals("Entregar Pedido", ignoreCase = true)
                    }

                    if (permissionToSendOrder.all { it.isEnabled }) {
                        ConfirmedOrdersFragment.instance!!.setListener(ConfirmedOrdersController.instance!!)
                        NewOrdersController.instance!!.setContext(this)
                        showFragment(OrdersAdminFragment.instance!!)
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