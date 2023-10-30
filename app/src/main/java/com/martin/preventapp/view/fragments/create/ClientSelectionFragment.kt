package com.martin.preventapp.view.fragments.create

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
import com.martin.preventapp.R
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.FragmentClientSelectionBinding
import com.martin.preventapp.databinding.FragmentCreateOrderBinding
import com.martin.preventapp.view.MainActivity

class ClientSelectionFragment : Fragment() {

    private var _binding: FragmentClientSelectionBinding? = null
    private val binding get() = _binding!!

    private var clientSelected : String = ""

    companion object {
        private var clientSelectionFragment: ClientSelectionFragment? = null
        @JvmStatic
        val instance: ClientSelectionFragment?
            get() {
                if (clientSelectionFragment == null) {
                    clientSelectionFragment = ClientSelectionFragment()
                }
                return clientSelectionFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientSelectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clientList = listOf("Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4")

        val clientAdapter = ClientAdapter(requireContext(), clientList)
        binding.clientList.adapter = clientAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (query != null && clientList.contains(query)) {
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
            clientSelected = clientAdapter.getItem(position).toString()
            binding.tvClient.text = "Cliente seleccionado: $clientSelected"
        }

        binding.nextStepClient.setOnClickListener {
            if(clientSelected == ""){
                Toast.makeText(requireContext(), "DEBE SELECCIONAR UN CLIENTE", Toast.LENGTH_LONG).show()
            }else{
                CreateOrderController.instance?.setClientSelected(clientSelected)
            }
        }

        binding.backButton.setOnClickListener {
            requireActivity().finish()
        }
    }
}