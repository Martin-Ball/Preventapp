package com.martin.preventapp.view.fragments.admin.audit.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.databinding.FragmentAuditOrdersBinding
import com.martin.preventapp.databinding.FragmentAuditUserBinding
import com.martin.preventapp.view.fragments.admin.audit.user.AuditUserFragment

class AuditOrdersFragment : Fragment() {
    private var _binding: FragmentAuditOrdersBinding? = null
    private val binding get() = _binding!!
    private var itemsToAudit : Array<String> = emptyArray()
    private var itemToAudit: String = ""

    companion object {
        private var auditOrdersFragment: AuditOrdersFragment? = null
        @JvmStatic
        val instance: AuditOrdersFragment?
            get() {
                if (auditOrdersFragment == null) {
                    auditOrdersFragment = AuditOrdersFragment()
                }
                return auditOrdersFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuditOrdersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsToAudit = arrayOf("Pedidos entregados", "Pedidos no entregados", "Pedidos cancelados", "Pedidos nuevos")

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