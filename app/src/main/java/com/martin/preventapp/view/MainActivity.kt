package com.martin.preventapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.martin.preventapp.R
import com.martin.preventapp.view.fragments.create.CreateOrderFragment
import com.martin.preventapp.databinding.ActivityMainBinding

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

        showFragment(CreateOrderFragment.instance!!)
    }

    private fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun showClientActivity(){

    }
}