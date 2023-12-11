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
                    val permissionToSendOrder = permissions.find { it.name == "Enviar Pedido" }

                    if(permissionToSendOrder?.isEnabled != false){
                        CreateOrderController.instance!!.setContext(this)
                        showFragment(CreateOrderFragment.instance!!)
                    }else{
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE
                    }

                    true
                }
                R.id.navigation_recommended -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder = permissions.find { it.name == "Recomendar Productos" }

                    if(permissionToSendOrder?.isEnabled != false){
                        RecommendedController.instance!!.setContext(this)
                        showFragment(RecommendedFragment.instance!!)
                    }else{
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE
                    }

                    true
                }
                R.id.navigation_orders -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder = permissions.find { it.name == "Orders" }

                    if(permissionToSendOrder?.isEnabled != false){
                        OrdersFragment.instance!!.setListener(OrdersController.instance!!)
                        ConfirmedOrdersController.instance!!.setContext(this)
                        showFragment(OrdersFragment.instance!!)
                    }else{
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE
                    }


                    true
                }
                R.id.navigation_profile -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder = permissions.find { it.name == "Perfil" }

                    if(permissionToSendOrder?.isEnabled != false){
                        ProfileController.instance!!.setContext(this)
                        showFragment(ProfileFragment.instance!!)
                    }else{
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE
                    }

                    true
                }
                else -> {
                    binding.tvEnabledAction.visibility = View.GONE
                    val permissionToSendOrder = permissions.find { it.name == "Enviar Pedido" }

                    if(permissionToSendOrder?.isEnabled != false){
                        CreateOrderController.instance!!.setContext(this)
                        showFragment(CreateOrderFragment.instance!!)
                    }else{
                        removeCurrentFragment()
                        binding.tvEnabledAction.visibility = View.VISIBLE
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