package com.martin.preventapp.view.fragments.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.FragmentDetailOrderBinding
import com.martin.preventapp.view.adapter.ProductResumeAdapter

class DetailOrderFragment : Fragment() {

    private var _binding: FragmentDetailOrderBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var detailOrderFragment: DetailOrderFragment? = null
        @JvmStatic
        val instance: DetailOrderFragment?
            get() {
                if (detailOrderFragment == null) {
                    detailOrderFragment = DetailOrderFragment()
                }
                return detailOrderFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}