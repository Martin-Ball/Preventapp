package com.martin.preventapp.view.fragments.seller.orders

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.OrdersInterface
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.databinding.FragmentOrdersBinding
import com.martin.preventapp.databinding.FragmentResumeBinding
import com.martin.preventapp.view.adapter.OrderAdapter
import com.martin.preventapp.view.adapter.OrderItemClickListener
import com.martin.preventapp.view.adapter.ProductResumeAdapter
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductOrder
import com.martin.preventapp.view.fragments.seller.create.ResumeFragment
import com.martin.preventapp.view.fragments.seller.recommended.RecommendedProductFragment
import java.util.Calendar

class OrdersFragment : Fragment(), OrdersInterface.ViewOrders {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: OrderItemClickListener

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
            OrderItem(listOf(
                Product("Producto 1", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11, 5),
                Product("Producto 12", "La paulina", "Unidad", "Unidad", 1212.11, 5),
            ), Client("Cliente 1", "Lavalle 1333", "10 a 15hs"), "Preventista 1", "nota de pedido"),
            )

        val adapter = OrderAdapter(requireContext(), items, listener)
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
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                binding.dateSelected.text = "Fecha: ${selectedDate}"
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    override fun showFragmentDetail() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container, DetailOrderFragment.instance!!)
        transaction.commit()
    }

    override fun setListener(listener: OrderItemClickListener) {
        this.listener = listener
    }
}