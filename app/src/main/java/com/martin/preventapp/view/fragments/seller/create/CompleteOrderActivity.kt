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

class CompleteOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClientSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSelectionBinding.inflate(layoutInflater);
        setContentView(binding.root)


    }

    public fun goToMain() {
        val intent = Intent(this, MainSellerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}