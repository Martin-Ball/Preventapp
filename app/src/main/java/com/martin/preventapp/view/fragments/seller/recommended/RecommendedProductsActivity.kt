package com.martin.preventapp.view.fragments.seller.recommended

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.RecommendedInterface
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.databinding.ActivityClientSelectionBinding
import com.martin.preventapp.databinding.ActivityRecommendedProductsBinding
import com.martin.preventapp.view.activities.seller.MainSellerActivity

class RecommendedProductsActivity : AppCompatActivity(), RecommendedInterface.ViewActivity {
    private lateinit var binding: ActivityRecommendedProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendedProductsBinding.inflate(layoutInflater);
        setContentView(binding.root)

        RecommendedController.instance!!.setView(this)
        RecommendedController.instance!!.setContext(this)

        showFragment(RecommendedProductFragment.instance!!)
    }

    override fun goToMain() {
        finish()
    }

    override fun showFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}