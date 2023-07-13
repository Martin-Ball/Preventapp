package com.martin.preventapp.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.martin.preventapp.R
import com.martin.preventapp.View.Fragments.LoginFragment
import com.martin.preventapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showSignInFragment()
    }

    private fun showSignInFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, LoginFragment.getInstance())
            .commit()
    }
}
