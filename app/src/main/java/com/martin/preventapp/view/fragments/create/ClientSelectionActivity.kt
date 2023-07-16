package com.martin.preventapp.view.fragments.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.martin.preventapp.R
import com.martin.preventapp.controller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.ActivityClientSelectionBinding
import com.martin.preventapp.databinding.ActivityMainBinding
import com.martin.preventapp.view.adapter.AmountAdapter
import com.martin.preventapp.view.adapter.ItemAmount

class ClientSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClientSelectionBinding
    private var clientSelected : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientSelectionBinding.inflate(layoutInflater);
        setContentView(binding.root)

        CreateOrderController.instance?.setViewClient(this)

        val clients = arrayOf("Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4", "Cliente 5", "Cliente 6", "Cliente 7", "Cliente 8")

        val clientsAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            clients
        )

        binding.clientList.adapter = clientsAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (clients.contains(query)) {
                    clientsAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                clientsAdapter.filter.filter(newText)
                return false
            }
        })

        binding.clientList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            clientSelected = clientsAdapter.getItem(position).toString()
            binding.tvClient.text = "Cliente seleccionado: $clientSelected"
        }

        binding.nextStepClient.setOnClickListener {
            if(clientSelected == ""){
                Toast.makeText(this, "DEBE SELECCIONAR UN CLIENTE", Toast.LENGTH_LONG)
            }else{
                CreateOrderController.instance?.setClientSelected(clientSelected)
            }
        }
    }
}