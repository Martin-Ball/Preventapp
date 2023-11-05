package com.martin.preventapp.view.fragments.admin.neworders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.databinding.ActivityClientSelectionBinding
import com.martin.preventapp.databinding.ActivityConfirmedOrdersBinding
import com.martin.preventapp.view.activities.admin.MainAdminActivity
import com.martin.preventapp.view.activities.seller.MainSellerActivity

class ConfirmedOrdersActivity : AppCompatActivity(), ConfirmedOrderInterface.ViewOrders {

    private lateinit var binding: ActivityConfirmedOrdersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmedOrdersBinding.inflate(layoutInflater);
        setContentView(binding.root)
        ConfirmedOrdersController.instance!!.setView(this)
        showFragment()
    }

    override fun showFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.completed_order_container, ConfirmedOrdersFragment.instance!!)
            .addToBackStack(null)
            .commit()
    }

    override fun goToMain() {
        val intent = Intent(this, MainAdminActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

}