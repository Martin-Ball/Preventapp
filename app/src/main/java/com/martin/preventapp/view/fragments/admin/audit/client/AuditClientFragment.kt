package com.martin.preventapp.view.fragments.admin.audit.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.databinding.FragmentAuditClientBinding

class AuditClientFragment : Fragment() {
    private var _binding: FragmentAuditClientBinding? = null
    private val binding get() = _binding!!
    private var itemsToAudit : Array<String> = emptyArray()
    private var itemToAudit: String = ""

    companion object {
        private var auditClientFragment: AuditClientFragment? = null
        @JvmStatic
        val instance: AuditClientFragment?
            get() {
                if (auditClientFragment == null) {
                    auditClientFragment = AuditClientFragment()
                }
                return auditClientFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuditClientBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = AuditController.instance!!.getClientSelected()

        itemsToAudit = arrayOf("Pedidos", "Compras")

        val adapter = ArrayAdapter(requireContext(), R.layout.item_rol, itemsToAudit)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerRol.adapter = adapter

        binding.spinnerRol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                itemToAudit = itemsToAudit[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.backButton.setOnClickListener {
            AuditController.instance!!.goToMain()
        }
    }
}