package com.martin.preventapp.view.fragments.seller.create

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.ActivityClientSelectionBinding
import com.martin.preventapp.view.activities.seller.MainSellerActivity

class CompleteOrderActivity : AppCompatActivity(), CreateOrderInterface.CompleteOrderView {

    private lateinit var binding: ActivityClientSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSelectionBinding.inflate(layoutInflater);
        setContentView(binding.root)

        CreateOrderController.instance?.setViewClient(this)
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun goToMain() {
        val intent = Intent(this, MainSellerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}