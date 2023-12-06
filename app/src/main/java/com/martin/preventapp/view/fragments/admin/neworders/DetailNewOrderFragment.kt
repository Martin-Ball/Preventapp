package com.martin.preventapp.view.fragments.admin.neworders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.databinding.FragmentDetailNewOrderBinding
import com.martin.preventapp.view.adapter.ProductResumeAdapter
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.ProductOrder

class DetailNewOrderFragment : Fragment() {
    private var _binding: FragmentDetailNewOrderBinding? = null
    private val binding get() = _binding!!

    private var itemToDetail: NewOrder? = null
    private var newOrder: Boolean = false

    companion object {
        private var detailNewOrderFragment: DetailNewOrderFragment? = null
        @JvmStatic
        val instance: DetailNewOrderFragment?
            get() {
                if (detailNewOrderFragment == null) {
                    detailNewOrderFragment = DetailNewOrderFragment()
                }
                return detailNewOrderFragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemToDetail = NewOrdersController.instance!!.getItemToDetail()
        newOrder = NewOrdersController.instance!!.getIsNewOrder()
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

        val productsAdapter = ProductResumeAdapter(requireContext(), itemToDetail!!.products.map { ProductOrder(
            it.productName,
            it.brand,
            it.presentation,
            it.unit,
            it.price,
            it.amount ?: 0
        ) })
        binding.listProducts.adapter = productsAdapter

        binding.tvNotes.text = itemToDetail!!.note

        binding.backButton.setOnClickListener {
            OrdersController.instance!!.setItemToDetail(null)
            requireActivity().onBackPressed()
        }

        binding.btnSendToDelivery.setOnClickListener {
            Toast.makeText(requireContext(), itemToDetail?.idOrder!!.toString(), Toast.LENGTH_LONG).show()
            NewOrdersController.instance!!.confirmOrder()
            requireActivity().onBackPressed()
        }

        binding.btnCancelOrder.setOnClickListener {
            Toast.makeText(requireContext(), "PEDIDO CANCELADO", Toast.LENGTH_LONG).show()
            NewOrdersController.instance!!.cancelOrder()
            requireActivity().onBackPressed()
        }
    }
}