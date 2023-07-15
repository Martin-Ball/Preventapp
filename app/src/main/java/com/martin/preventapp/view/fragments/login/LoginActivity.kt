package com.martin.preventapp.view.fragments.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.martin.preventapp.controller.interfaces.LoginInterfaces
import com.martin.preventapp.controller.login.LoginController
import com.martin.preventapp.R
import com.martin.preventapp.databinding.ActivityLoginBinding
import com.martin.preventapp.view.MainActivity

class LoginActivity : AppCompatActivity(), LoginInterfaces.View {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showSignInFragment()
        LoginController.instance!!.setView(this)
        LoginController.instance!!.setContext(this)
    }

    private fun showSignInFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, LoginFragment.instance!!)
            .addToBackStack(null)
            .commit()
    }

    override fun goMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
