package com.martin.preventapp.view.fragments.seller.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.OrdersInterface
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.databinding.FragmentDetailOrderBinding
import com.martin.preventapp.view.adapter.ProductResumeAdapter
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.ProductOrder

class DetailOrderFragment : Fragment() {

    private var _binding: FragmentDetailOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemToDetail: NewOrder

    companion object {
        private var detailOrderFragment: DetailOrderFragment? = null
        @JvmStatic
        val instance: DetailOrderFragment?
            get() {
                if (detailOrderFragment == null) {
                    detailOrderFragment = DetailOrderFragment()
                }
                return detailOrderFragment
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item = OrdersController.instance!!.getItemToDetail()
        if(item != null) {
            itemToDetail = item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvClient.text = itemToDetail.client.name
        val productsAdapter = ProductResumeAdapter(requireContext(), itemToDetail!!.products.map { ProductOrder(
            it.productName,
            it.brand,
            it.presentation,
            it.unit,
            it.price,
            it.amount ?: 0
        ) })
        binding.listProducts.adapter = productsAdapter

        binding.tvNotes.text = itemToDetail.note

        binding.backButton.setOnClickListener {
            OrdersController.instance!!.setItemToDetail(null)
            requireActivity().onBackPressed()
        }
    }


}