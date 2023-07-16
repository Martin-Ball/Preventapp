package com.martin.preventapp.view.fragments.create

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.view.adapter.AmountAdapter
import com.martin.preventapp.view.adapter.ItemAmount

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

        val products = arrayOf("Queso", "Bondiola", "Jamon", "Mayonesa", "Vino", "Salame", "Vacio")
        val itemList: MutableList<ItemAmount> = mutableListOf()

        val productsAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1,
            products
        )


        val adapter = AmountAdapter(itemList)
        binding.rvAmount.adapter = adapter
        binding.rvAmount.layoutManager = LinearLayoutManager(requireContext())

        binding.productList.adapter = productsAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (products.contains(query)) {
                    productsAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productsAdapter.filter.filter(newText)
                return false
            }
        })

        binding.productList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = productsAdapter.getItem(position)
            adapter.addItem(ItemAmount(selectedItem.toString()))
        }

        binding.nextStepOrder.setOnClickListener {
            if(itemList.isEmpty()){
                Toast.makeText(requireContext(), "DEBE SELECCIONAR PRODUCTOS", Toast.LENGTH_LONG)
            }else{
                CreateOrderController.instance?.goToStepClient(itemList)
            }

        }


    }

    override fun showClientActivity(){
        requireActivity().startActivity(Intent(requireContext(), ClientSelectionActivity::class.java))
    }
}