package com.martin.preventapp.view.fragments.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.FragmentResumeBinding
import com.martin.preventapp.view.entities.ItemAmount

class ResumeFragment : Fragment() {

    private var _binding: FragmentResumeBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var resumeFragment: ResumeFragment? = null
        @JvmStatic
        val instance: ResumeFragment?
            get() {
                if (resumeFragment == null) {
                    resumeFragment = ResumeFragment()
                }
                return resumeFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var order = CreateOrderController.instance?.getOrder()

        binding.tvClient.text = order!!.client
        binding.tvNotes.text = order.notes

        val productsAdapter: ArrayAdapter<ItemAmount> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            order.products
        )

        binding.listProducts.adapter = productsAdapter

        binding.sendOrder.setOnClickListener {
            order.notes = binding.etNotes.toString()
            CreateOrderController.instance?.sendOrder(order)
            Toast.makeText(requireContext(), "PEDIDO ENVIADO: \nCLIENTE: ${order.client} \n" +
                    "PRODUCTOS: ${order.products} \n" +
                    "NOTAS: ${order.notes}", Toast.LENGTH_LONG).show()
        }
    }


}