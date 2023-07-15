package com.martin.preventapp.view.fragments.create

import android.R
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.controller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.view.adapter.ProductAdapter

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

        val products = listOf("Queso", "Bondiola", "Jamon", "Mayonesa", "Vino", "Salame", "Vacio")

        val userAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1,
            products
        )

        binding.userList.adapter = ProductAdapter(requireContext(), products)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (products.contains(query)) {
                    userAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                return false
            }
        })

        binding.userList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = userAdapter.getItem(position)
            CreateOrderController.instance!!.onProductSelected(selectedItem!!)

        }
    }

    private fun onItemSelected(item : String){
        Toast.makeText(requireContext(),item, Toast.LENGTH_LONG).show()
    }
}