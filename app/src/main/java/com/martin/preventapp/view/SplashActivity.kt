package com.martin.preventapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.martin.preventapp.R
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.databinding.ActivitySplashBinding
import com.martin.preventapp.view.fragments.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_screen)
        binding.tvSplash.startAnimation(animation)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}