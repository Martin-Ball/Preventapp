package com.martin.preventapp.view.fragments.seller.create

import ClientAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.databinding.FragmentClientSelectionBinding
import com.martin.preventapp.view.entities.Client

class ClientSelectionFragment : Fragment(), CreateOrderInterface.ClientSelectionView {

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

    override fun showClients(list: List<Client>) {
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
            clientSelected = clientAdapter.getItem(position).toString()
            binding.tvClient.text = "Cliente seleccionado: $clientSelected"
        }
    }
}