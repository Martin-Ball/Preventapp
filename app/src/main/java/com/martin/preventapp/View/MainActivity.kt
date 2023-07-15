package com.martin.preventapp.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.View.Fragments.Create.CreateOrderFragment
import com.martin.preventapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        showFragment(CreateOrderFragment.instance!!)
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}