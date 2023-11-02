package com.martin.preventapp.view.fragments.Profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentOrdersBinding
import com.martin.preventapp.databinding.FragmentProfileBinding
import com.martin.preventapp.view.fragments.orders.OrdersFragment

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var profileFragment: ProfileFragment? = null
        @JvmStatic
        val instance: ProfileFragment?
            get() {
                if (profileFragment == null) {
                    profileFragment = ProfileFragment()
                }
                return profileFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}