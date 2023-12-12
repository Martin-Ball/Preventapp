package com.martin.preventapp.view.fragments.admin.neworders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.databinding.ActivityConfirmedOrdersBinding
import com.martin.preventapp.view.activities.admin.MainAdminActivity
import com.martin.preventapp.view.activities.delivery.MainDeliveryActivity
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveredFragment

class ConfirmedOrdersActivity : AppCompatActivity(), ConfirmedOrderInterface.ViewOrders {

    private lateinit var binding: ActivityConfirmedOrdersBinding
    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmedOrdersBinding.inflate(layoutInflater);
        setContentView(binding.root)
        isAdmin = intent.getBooleanExtra("isAdmin", false)
        ConfirmedOrdersController.instance!!.setView(this)
        if(isAdmin){
            showFragment(ConfirmedOrdersFragment.instance!!)
        }else{
            showFragment(OrdersDeliveredFragment.instance!!)
        }
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.completed_order_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showFragmentDetail() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.completed_order_container, DetailNewOrderFragment(NewOrdersController.instance!!))
            .addToBackStack(null)
            .commit()
    }

    override fun goToMain() {
        if(isAdmin){
            val intent = Intent(this, MainAdminActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, MainDeliveryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }

    }

}