package com.martin.preventapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.martin.preventapp.R
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.controller.seller.interfaces.LoginInterfaces
import com.martin.preventapp.databinding.ActivitySplashBinding
import com.martin.preventapp.view.activities.admin.MainAdminActivity
import com.martin.preventapp.view.activities.delivery.MainDeliveryActivity
import com.martin.preventapp.view.activities.seller.MainSellerActivity
import com.martin.preventapp.view.fragments.login.LoginActivity

class SplashActivity : AppCompatActivity(), LoginInterfaces.ViewSplash {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        LoginController.instance!!.setViewSplash(this)
        LoginController.instance!!.setContext(this)

        LoginController.instance!!.validateToken()

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen)
        binding.tvSplash.startAnimation(animation)
    }

    override fun intent() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }

    override fun goToActivitySeller() {
        Handler().postDelayed({
            startActivity(Intent(this, MainSellerActivity::class.java))
            finish()
        }, 2000)
    }

    override fun goToActivityAdmin() {
        Handler().postDelayed({
            startActivity(Intent(this, MainAdminActivity::class.java))
            finish()
        }, 2000)
    }

    override fun goToActivityDelivery() {
        Handler().postDelayed({
            startActivity(Intent(this, MainDeliveryActivity::class.java))
            finish()
        }, 2000)
    }
}