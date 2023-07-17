package com.martin.preventapp.view.fragments.recommended

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.martin.preventapp.databinding.ActivityRecommendedProductsBinding

class RecommendedProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecommendedProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendedProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}