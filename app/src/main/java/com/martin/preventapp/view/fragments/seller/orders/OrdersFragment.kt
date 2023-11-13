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
            OrderItem("Pedido 1", listOf(
                Product("Producto 1", 1212.11), Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11),
                Product("Producto 12", 1212.11)
            ), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
            OrderItem("Pedido 1", listOf(Product("Producto 1", 1212.11), Product("Producto 12", 1212.11)), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
            OrderItem("Pedido 2", listOf(Product("Producto 2", 1212.11), Product("Producto 13", 1212.11)), Client("Cliente 2"),"Preventista 2", "nota de pedido1"),
            OrderItem("Pedido 3", listOf(Product("Producto 3", 1212.11), Product("Producto 14", 1212.11)), Client("Cliente 3"),"Preventista 1", "nota de pedido2"),
            OrderItem("Pedido 4", listOf(Product("Producto 4", 1212.11), Product("Producto 15", 1212.11)), Client("Cliente 4"),"Preventista 2", "nota de pedido3"),
            OrderItem("Pedido 5", listOf(Product("Producto 5", 1212.11), Product("Producto 16", 1212.11)), Client("Cliente 5"),"Preventista 2", "nota de pedido4"),
            OrderItem("Pedido 6", listOf(Product("Producto 6", 1212.11), Product("Producto 17", 1212.11)), Client("Cliente 6"),"Preventista 1", "nota de pedido5"),
            OrderItem("Pedido 7", listOf(Product("Producto 7", 1212.11), Product("Producto 18", 1212.11)), Client("Cliente 7"),"Preventista 2", "nota de pedido6"),
            OrderItem("Pedido 8", listOf(Product("Producto 8", 1212.11), Product("Producto 19", 1212.11)), Client("Cliente 8"),"Preventista 1", "nota de pedido7"),
            OrderItem("Pedido 9", listOf(Product("Producto 9", 1212.11), Product("Producto 20", 1212.11)), Client("Cliente 9"), "Preventista 2","nota de pedido7"),
            OrderItem("Pedido 10", listOf(Product("Producto 10", 1212.11), Product("Producto 21", 1212.11)), Client("Cliente 10"),"Preventista 1", "nota de pedido9"),
            OrderItem("Pedido 11", listOf(Product("Producto 11", 1212.11), Product("Producto 22", 1212.11)), Client("Cliente 11"),"Preventista 2", "nota de pedido10"),

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