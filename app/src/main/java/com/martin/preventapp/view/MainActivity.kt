package com.martin.preventapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.view.fragments.create.CreateOrderFragment
import com.martin.preventapp.databinding.ActivityMainBinding
import java.util.UUID

class MainActivity : AppCompatActivity() {

    companion object {
        private var mainActivity: MainActivity? = null
        @JvmStatic
        val instance: MainActivity?
            get() {
                if (mainActivity == null) {
                    mainActivity = MainActivity()
                }
                return mainActivity
            }
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
           when (menuItem.itemId) {
                R.id.navigation_list -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
                R.id.navigation_recommended -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
                R.id.navigation_orders -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
                R.id.navigation_profile -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
                else -> {
                    showFragment(CreateOrderFragment.instance!!)
                    true
                }
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.navigation_list
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}