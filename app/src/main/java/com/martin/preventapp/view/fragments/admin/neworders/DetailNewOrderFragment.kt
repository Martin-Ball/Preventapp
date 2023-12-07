package com.martin.preventapp.view.fragments.admin.neworders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.martin.preventapp.controller.admin.interfaces.OrderDetail
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.databinding.FragmentDetailNewOrderBinding
import com.martin.preventapp.view.adapter.ProductResumeAdapter
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.ProductOrder

class DetailNewOrderFragment(private val orderController: OrderDetail? = null) : Fragment() {
    private var _binding: FragmentDetailNewOrderBinding? = null
    private val binding get() = _binding!!

    private var itemToDetail: NewOrder? = null
    private var newOrder: Boolean = false

    companion object {
        private var detailNewOrderFragment: DetailNewOrderFragment? = null
        @JvmStatic
        fun getInstance(orderController: OrderDetail? = null): DetailNewOrderFragment {
            if (detailNewOrderFragment == null) {
                detailNewOrderFragment = DetailNewOrderFragment(orderController)
            }
            return detailNewOrderFragment!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemToDetail = orderController!!.getItemToDetail()
        newOrder = orderController.getIsNewOrder()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvClient.text = itemToDetail!!.client.name
        binding.llSeller.visibility = View.VISIBLE
        binding.tvTitleSeller.visibility = View.VISIBLE
        binding.tvTitleState.visibility = View.VISIBLE
        binding.llState.visibility = View.VISIBLE
        binding.tvSeller.text = itemToDetail!!.seller
        binding.tvState.text = itemToDetail!!.state
        binding.btnSendToDelivery.isVisible = newOrder
        binding.btnCancelOrder.isVisible = newOrder

        if(orderController!!.getIsAdmin()){
            binding.btnOrderDelivered.visibility = View.VISIBLE
            binding.btnNotDeliver.visibility = View.VISIBLE
            binding.btnOrderDelivered.setOnClickListener {
                orderController.orderDelivered()
                requireActivity().onBackPressed()
            }

            binding.btnNotDeliver.setOnClickListener {
                orderController.notDeliverOrder()
                requireActivity().onBackPressed()
            }
        }

        val productsAdapter = ProductResumeAdapter(requireContext(), itemToDetail!!.products.map { ProductOrder(
            it.productName,
            it.brand,
            it.presentation,
            it.unit,
            it.price,
            it.amount ?: 0
        ) })
        binding.listProducts.adapter = productsAdapter

        binding.tvNote.text = itemToDetail!!.note

        binding.backButton.setOnClickListener {
            orderController.setItemToDetail(null, false, 0, false)
            requireActivity().onBackPressed()
        }

        binding.btnSendToDelivery.setOnClickListener {
            Toast.makeText(requireContext(), itemToDetail?.idOrder!!.toString(), Toast.LENGTH_LONG).show()
            orderController.confirmOrder()
            requireActivity().onBackPressed()
        }

        binding.btnCancelOrder.setOnClickListener {
            Toast.makeText(requireContext(), "PEDIDO CANCELADO", Toast.LENGTH_LONG).show()
            orderController.cancelOrder()
            requireActivity().onBackPressed()
        }
    }
}
