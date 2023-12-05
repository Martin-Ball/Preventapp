package com.martin.preventapp.view.fragments.admin.neworders

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.databinding.FragmentConfirmedOrdersBinding
import com.martin.preventapp.view.adapter.OrderAdapter
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductOrder
import java.util.Calendar

class ConfirmedOrdersFragment : Fragment(), ConfirmedOrderInterface.listener {
    private var _binding: FragmentConfirmedOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: OrderItemClickListener

    companion object {
        private var ordersFragment: ConfirmedOrdersFragment? = null
        @JvmStatic
        val instance: ConfirmedOrdersFragment?
            get() {
                if (ordersFragment == null) {
                    ordersFragment = ConfirmedOrdersFragment()
                }
                return ordersFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmedOrdersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            OrderItem(listOf(
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
            ), "2023-12-04", Client("Cliente 1", "Lavalle 1333", "10 a 15hs"), "Preventista 1", "nota de pedido"),
            OrderItem(listOf(
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
            ), "2023-12-04", Client("Cliente 1", "Lavalle 1333", "10 a 15hs"), "Preventista 1", "nota de pedido"),
            )

        val adapter = OrderAdapter(requireContext(), items, listener)
        binding.orderList.adapter = adapter

        binding.btnOpenDatePicker.setOnClickListener {
            showDatePicker()
        }

        binding.backButton.setOnClickListener {
            ConfirmedOrdersController.instance!!.goToMain()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                binding.dateSelected.text = "Fecha: ${selectedDate}"
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    override fun setListener(listener: OrderItemClickListener) {
        this.listener = listener
    }

}