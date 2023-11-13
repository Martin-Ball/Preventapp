package com.martin.preventapp.view.fragments.admin.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentListBinding
import com.martin.preventapp.databinding.FragmentOrdersAdminBinding
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersActivity
import com.martin.preventapp.view.fragments.admin.neworders.OrdersAdminFragment

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var listFragment: ListFragment? = null
        @JvmStatic
        val instance: ListFragment?
            get() {
                if (listFragment == null) {
                    listFragment = ListFragment()
                }
                return listFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewList.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), ListSelectionActivity::class.java))
        }

        binding.btnNewClients.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), ClientListActivity::class.java))
        }

        binding.btnViewList.setOnClickListener {

        }

        binding.btnViewClients.setOnClickListener {

        }
    }

}