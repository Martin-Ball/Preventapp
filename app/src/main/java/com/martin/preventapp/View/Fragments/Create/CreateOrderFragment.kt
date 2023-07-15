package com.martin.preventapp.View.Fragments.Create

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
import com.martin.preventapp.Controller.LoginController
import com.martin.preventapp.View.Fragments.Login.LoginFragment
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.databinding.FragmentLoginBinding

class CreateOrderFragment : Fragment() {

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
    ): View? {
        _binding = FragmentCreateOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = arrayOf("Queso", "Bondiola", "Jamon", "Mayonesa", "Vino", "Salame", "Vacio")

        val userAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1,
            products
        )

        binding.userList.adapter = userAdapter

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
            onItemSelected(selectedItem!!)

        }

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(view)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog?.dismiss()
            }

        builder.create()
    }

    private fun onItemSelected(item : String){
        Toast.makeText(requireContext(),item, Toast.LENGTH_LONG)
    }
}