package com.martin.preventapp.view.fragments.orders

import android.app.DatePickerDialog
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
import java.util.Calendar

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

        binding.btnOpenDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            },
            year, month, day
        )

        datePickerDialog.show()
    }


}