package com.martin.preventapp.view.fragments.admin.neworders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.NewOrdersController
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.databinding.FragmentDetailNewOrderBinding
import com.martin.preventapp.databinding.FragmentDetailOrderBinding
import com.martin.preventapp.view.adapter.ProductResumeAdapter
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.fragments.seller.orders.DetailOrderFragment

class DetailNewOrderFragment : Fragment() {
    private var _binding: FragmentDetailNewOrderBinding? = null
    private val binding get() = _binding!!

    private var itemToDetail: OrderItem? = null
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
        binding.tvSeller.text = itemToDetail!!.seller
        binding.btnSendToDelivery.isVisible = newOrder
        binding.btnCancelOrder.isVisible = newOrder

        val productsAdapter = ProductResumeAdapter(requireContext(), itemToDetail!!.products)
        binding.listProducts.adapter = productsAdapter

        binding.tvNotes.text = itemToDetail!!.note

        binding.backButton.setOnClickListener {
            OrdersController.instance!!.setItemToDetail(null)
            requireActivity().onBackPressed()
        }
    }
}