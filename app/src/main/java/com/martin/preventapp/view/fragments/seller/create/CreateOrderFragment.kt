package com.martin.preventapp.view.fragments.seller.create

import ProductListAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.view.adapter.AmountAdapter
import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product

class CreateOrderFragment : Fragment(), CreateOrderInterface.View {

    @JvmField
    var context: Activity? = null
    @JvmField
    var view: View? = null

    private var _binding: FragmentCreateOrderBinding? = null
    private val binding get() = _binding!!
    private var itemList: MutableList<ItemAmount> = mutableListOf()

    companion object {
        private var createOrderFragment: CreateOrderFragment? = null
        @JvmStatic
        val instance: CreateOrderFragment?
            get() {
                if (createOrderFragment == null) {
                    createOrderFragment = CreateOrderFragment()
                }
                return createOrderFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CreateOrderController.instance!!.getListProducts()

        binding.nextStepOrder.setOnClickListener {
            if(itemList.isEmpty()){
                Toast.makeText(requireContext(), "DEBE SELECCIONAR PRODUCTOS", Toast.LENGTH_LONG).show()
            }else{
                CreateOrderController.instance?.goToStepClient(itemList)
            }

        }
    }

    override fun showClientActivity(){
        requireActivity().startActivity(Intent(requireContext(), CompleteOrderActivity::class.java))
    }

    override fun showListPrices(list: List<Product>) {
        val productsAdapter = ProductListAdapter(requireContext(), list)
        binding.productList.adapter = productsAdapter


        val adapter = AmountAdapter(itemList)
        binding.rvAmount.adapter = adapter
        binding.rvAmount.layoutManager = LinearLayoutManager(requireContext())

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (query != null) {
                    productsAdapter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    productsAdapter.filter(newText)
                }
                return false
            }
        })

        binding.productList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = productsAdapter.getItem(position)
            if(!adapter.checkIfExist(selectedItem.productName)) {
                adapter.addItem(ItemAmount(
                    selectedItem.productName,
                    selectedItem.brand,
                    selectedItem.presentation,
                    selectedItem.unit,
                    selectedItem.price))
            }
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}