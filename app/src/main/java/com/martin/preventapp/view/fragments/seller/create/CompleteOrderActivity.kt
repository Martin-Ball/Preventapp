package com.martin.preventapp.view.fragments.seller.create

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.ActivityClientSelectionBinding
import com.martin.preventapp.view.activities.seller.MainSellerActivity
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.fragments.seller.orders.DetailOrderFragment

class CompleteOrderActivity : AppCompatActivity(), CreateOrderInterface.CompleteOrderView {

    private lateinit var binding: ActivityClientSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSelectionBinding.inflate(layoutInflater);
        setContentView(binding.root)

        CreateOrderController.instance!!.setViewClient(this)
        CreateOrderController.instance!!.setContext(this)
    }

    override fun goToMain() {
        val intent = Intent(this, MainSellerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun showFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun showLoader(boolean: Boolean) {
        binding.progressBar.visibility = if (boolean) View.VISIBLE else View.GONE
    }
}