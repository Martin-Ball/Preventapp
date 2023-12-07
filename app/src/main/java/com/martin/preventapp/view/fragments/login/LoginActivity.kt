package com.martin.preventapp.view.fragments.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.R
import com.martin.preventapp.databinding.ActivityLoginBinding
import com.martin.preventapp.view.activities.admin.MainAdminActivity
import com.martin.preventapp.view.activities.delivery.MainDeliveryActivity
import com.martin.preventapp.view.activities.seller.MainSellerActivity

class LoginActivity : AppCompatActivity(), LoginInterfaces.View {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoginController.instance!!.setView(this)
        LoginController.instance!!.setContext(this)
    }

    override fun showSignInFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment.instance!!)
            .addToBackStack(null)
            .commit()
    }

    override fun goToActivitySeller() {
        startActivity(Intent(this, MainSellerActivity::class.java))
        finish()
    }

    override fun goToActivityAdmin() {
        startActivity(Intent(this, MainAdminActivity::class.java))
        finish()
    }

    override fun goToActivityDelivery() {
        startActivity(Intent(this, MainDeliveryActivity::class.java))
        finish()
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun goToRegister() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RegisterFragment.instance!!)
            .addToBackStack(null)
            .commit()
    }
}
