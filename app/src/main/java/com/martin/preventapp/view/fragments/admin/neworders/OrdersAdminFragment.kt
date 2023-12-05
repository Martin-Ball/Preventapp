package com.martin.preventapp.view.fragments.admin.neworders

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.databinding.FragmentOrdersAdminBinding
import com.martin.preventapp.view.adapter.NewOrdersAdapter
import com.martin.preventapp.view.entities.NewOrder

class OrdersAdminFragment : Fragment(), NewOrderInterface.ViewOrders {
    private var _binding: FragmentOrdersAdminBinding? = null
    private val binding get() = _binding!!

    private var isEnabled: Boolean = true

    companion object {
        private var ordersAdminFragment: OrdersAdminFragment? = null
        @JvmStatic
        val instance: OrdersAdminFragment?
            get() {
                if (ordersAdminFragment == null) {
                    ordersAdminFragment = OrdersAdminFragment()
                }
                return ordersAdminFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersAdminBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            isEnabled = requireArguments().getBoolean("isEnabled", true)
        }

        if(isEnabled) {
            NewOrdersController.instance!!.getNewOrders()
        }else{
            binding.tvNewClient.visibility = View.GONE
            binding.orderList.visibility = View.GONE
            binding.btnConfirmOrders.visibility = View.GONE
            binding.btnExport.visibility = View.GONE

            binding.tvEnabledAction.visibility = View.VISIBLE
        }

    }

    override fun showFragmentDetail() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container, DetailNewOrderFragment .instance!!)
        transaction.commit()
    }

    override fun showOrdersList(list: List<NewOrder>) {
        val adapter = NewOrdersAdapter(requireContext(), list)
        binding.orderList.adapter = adapter

        binding.btnConfirmOrders.setOnClickListener {
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    ConfirmedOrdersActivity::class.java
                )
            )
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}