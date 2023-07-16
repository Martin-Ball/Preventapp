package com.martin.preventapp.view.fragments.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.ActivityClientSelectionBinding

class CompleteOrderActivity : AppCompatActivity(), CreateOrderInterface.CompleteOrderView {

    private lateinit var binding: ActivityClientSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSelectionBinding.inflate(layoutInflater);
        setContentView(binding.root)

        CreateOrderController.instance?.setViewClient(this)
    }

    override fun showClientFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}