package com.martin.preventapp.view.fragments.admin.neworders

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.databinding.FragmentOrdersAdminBinding
import com.martin.preventapp.view.adapter.NewOrdersAdapter
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.fragments.seller.create.CompleteOrderActivity
import com.martin.preventapp.view.fragments.seller.orders.DetailOrderFragment

class OrdersAdminFragment : Fragment(), NewOrderInterface.ViewOrders {
    private var _binding: FragmentOrdersAdminBinding? = null
    private val binding get() = _binding!!

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

        val items = listOf(
            OrderItem("Pedido 1", listOf(Product("Producto 1"), Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12"),Product("Producto 12")), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
            OrderItem("Pedido 1", listOf(Product("Producto 1"), Product("Producto 12")), Client("Cliente 1"), "Preventista 1", "nota de pedido"),
            OrderItem("Pedido 2", listOf(Product("Producto 2"), Product("Producto 13")), Client("Cliente 2"),"Preventista 2", "nota de pedido1"),
            OrderItem("Pedido 3", listOf(Product("Producto 3"), Product("Producto 14")), Client("Cliente 3"),"Preventista 1", "nota de pedido2"),
            OrderItem("Pedido 4", listOf(Product("Producto 4"), Product("Producto 15")), Client("Cliente 4"),"Preventista 2", "nota de pedido3"),
            OrderItem("Pedido 5", listOf(Product("Producto 5"), Product("Producto 16")), Client("Cliente 5"),"Preventista 2", "nota de pedido4"),
            OrderItem("Pedido 6", listOf(Product("Producto 6"), Product("Producto 17")), Client("Cliente 6"),"Preventista 1", "nota de pedido5"),
            OrderItem("Pedido 7", listOf(Product("Producto 7"), Product("Producto 18")), Client("Cliente 7"),"Preventista 2", "nota de pedido6"),
            OrderItem("Pedido 8", listOf(Product("Producto 8"), Product("Producto 19")), Client("Cliente 8"),"Preventista 1", "nota de pedido7"),
            OrderItem("Pedido 9", listOf(Product("Producto 9"), Product("Producto 20")), Client("Cliente 9"), "Preventista 2","nota de pedido7"),
            OrderItem("Pedido 10", listOf(Product("Producto 10"), Product("Producto 21")), Client("Cliente 10"),"Preventista 1", "nota de pedido9"),
            OrderItem("Pedido 11", listOf(Product("Producto 11"), Product("Producto 22")), Client("Cliente 11"),"Preventista 2", "nota de pedido10"),

            )

        val adapter = NewOrdersAdapter(requireContext(), items)
        binding.orderList.adapter = adapter

        binding.btnConfirmOrders.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(), ConfirmedOrdersActivity::class.java))
        }

    }

    override fun showFragmentDetail() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container, DetailNewOrderFragment .instance!!)
        transaction.commit()
    }
}