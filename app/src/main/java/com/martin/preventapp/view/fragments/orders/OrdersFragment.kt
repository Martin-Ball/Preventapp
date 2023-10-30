package com.martin.preventapp.view.fragments.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.martin.preventapp.R
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.databinding.FragmentOrdersBinding
import com.martin.preventapp.databinding.FragmentResumeBinding
import com.martin.preventapp.view.adapter.OrderAdapter
import com.martin.preventapp.view.adapter.ProductResumeAdapter
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.create.ResumeFragment

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var ordersFragment: OrdersFragment? = null
        @JvmStatic
        val instance: OrdersFragment?
            get() {
                if (ordersFragment == null) {
                    ordersFragment = OrdersFragment()
                }
                return ordersFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            OrderItem("Pedido 1"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
            OrderItem("Pedido 2"),
        )

        val adapter = OrderAdapter(requireContext(), items)
        binding.orderList.adapter = adapter
    }


}