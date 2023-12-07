package com.martin.preventapp.view.fragments.delivery

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
import com.martin.preventapp.controller.delivery.interfaces.OrdersToDeliverInterface
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.databinding.FragmentOrdersDeliveryBinding
import com.martin.preventapp.view.adapter.NewOrdersAdapter
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersActivity
import com.martin.preventapp.view.fragments.admin.neworders.DetailNewOrderFragment

class OrdersDeliveryFragment : Fragment(), OrdersToDeliverInterface.View {
    private var _binding: FragmentOrdersDeliveryBinding? = null
    private val binding get() = _binding!!

    private lateinit var listOrders: List<NewOrder>
    private lateinit var adapter: NewOrdersAdapter

    companion object {
        private var ordersAdminFragment: OrdersDeliveryFragment? = null
        @JvmStatic
        val instance: OrdersDeliveryFragment?
            get() {
                if (ordersAdminFragment == null) {
                    ordersAdminFragment = OrdersDeliveryFragment()
                }
                return ordersAdminFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersDeliveryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        OrdersToDeliverController.instance!!.getNewOrders(false)

    }

    override fun showFragmentDetail() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container, DetailNewOrderFragment.getInstance(OrdersToDeliverController.instance!!))
        transaction.commit()
    }

    override fun showOrdersList(list: List<NewOrder>) {
        if(list.isEmpty()){
            binding.tvEmptyOrders.visibility = View.VISIBLE
        }else{
            listOrders = list
            binding.tvEmptyOrders.visibility = View.GONE
        }

        adapter = NewOrdersAdapter(requireContext(), list, false, OrdersToDeliverController.instance!!)
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