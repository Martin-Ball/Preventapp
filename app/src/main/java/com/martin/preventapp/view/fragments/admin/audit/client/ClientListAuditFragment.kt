package com.martin.preventapp.view.fragments.admin.audit.client

import ClientAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.FragmentAuditClientBinding
import com.martin.preventapp.databinding.FragmentClientListAuditBinding
import com.martin.preventapp.view.entities.Client

class ClientListAuditFragment : Fragment() {
    private var _binding: FragmentClientListAuditBinding? = null
    private val binding get() = _binding!!
    private var clientSelected : String = ""
    private lateinit var listClient: List<Client>
    private lateinit var clientAdapter: ClientAdapter

    companion object {
        private var clientListAuditFragment: ClientListAuditFragment? = null
        @JvmStatic
        val instance: ClientListAuditFragment?
            get() {
                if (clientListAuditFragment == null) {
                    clientListAuditFragment = ClientListAuditFragment()
                }
                return clientListAuditFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientListAuditBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AuditController.instance!!.getClients()

        binding.nextStepClient.setOnClickListener {
            if(clientSelected == ""){
                Toast.makeText(requireContext(), "DEBE SELECCIONAR UN CLIENTE", Toast.LENGTH_LONG).show()
            }else{
                AuditController.instance?.setClientSelected(clientSelected)
            }
        }

        binding.backButton.setOnClickListener {
            requireActivity().finish()
        }
    }

    fun showClients(list: List<Client>) {
        val clientNames = list.map { it.name }
        listClient = list
        clientAdapter = ClientAdapter(requireContext(), list)
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