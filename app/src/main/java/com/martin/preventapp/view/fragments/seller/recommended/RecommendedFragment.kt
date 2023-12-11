package com.martin.preventapp.view.fragments.seller.recommended

import ClientAdapter
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.RecommendedInterface
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.databinding.FragmentRecommendedBinding
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.fragments.admin.users.UserManagerActivity

class RecommendedFragment : Fragment(), RecommendedInterface.View {

    companion object {
        private var recommendedFragment: RecommendedFragment? = null
        @JvmStatic
        val instance: RecommendedFragment?
            get() {
                if (recommendedFragment == null) {
                    recommendedFragment = RecommendedFragment()
                }
                return recommendedFragment
            }
    }

    private var _binding: FragmentRecommendedBinding? = null
    private val binding get() = _binding!!

    private var clientSelected : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RecommendedController.instance!!.getClientList()
    }

    override fun showClientList(list: List<Client>) {
        val clientNames = list.map { it.name }

        val clientAdapter = ClientAdapter(requireContext(), list)
        binding.clientList.adapter = clientAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (query != null && clientNames.contains(query)) {
                    clientAdapter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    clientAdapter.filter(newText)
                }
                return false
            }
        })

        binding.clientList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            clientSelected = clientAdapter.getItem(position)
            binding.tvClient.text = "Cliente seleccionado: $clientSelected"
        }

        binding.btnViewProducts.setOnClickListener {
            if(clientSelected == ""){
                Toast.makeText(requireContext(), "DEBE SELECCIONAR UN CLIENTE", Toast.LENGTH_LONG).show()
            }else{
                RecommendedController.instance?.getRecommendedProducts(clientSelected)
            }
        }
    }

    override fun showRecommendedProducts() {
        val intent = Intent(requireContext(), RecommendedProductsActivity::class.java)
        requireActivity().startActivity(intent)
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}