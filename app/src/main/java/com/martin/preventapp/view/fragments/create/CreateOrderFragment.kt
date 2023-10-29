package com.martin.preventapp.view.fragments.create

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
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.controller.interfaces.CreateOrderInterface
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

        val itemList: MutableList<ItemAmount> = mutableListOf()

        val products = listOf(Product("Queso"),
            Product("Bondiola"),
            Product("Jamon"),
            Product("Mayonesa"),
            Product("Vino"),
            Product("Salame"),
            Product("Vacio"))

        val productsAdapter = ProductListAdapter(requireContext(), products)
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
            if(!adapter.checkIfExist(selectedItem.title)) {
                adapter.addItem(ItemAmount(selectedItem.title))
            }
        }

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
}