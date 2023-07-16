package com.martin.preventapp.view.fragments.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.martin.preventapp.R
import com.martin.preventapp.controller.createOrder.CreateOrderController

class ClientSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_selection)

        CreateOrderController.instance?.setViewClient(this)
    }
}