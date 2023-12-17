package com.martin.preventapp.view.fragments.admin.audit.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.databinding.FragmentAuditUserBinding
import com.martin.preventapp.view.fragments.admin.neworders.DetailNewOrderFragment

class AuditUserFragment : Fragment() {
    private var _binding: FragmentAuditUserBinding? = null
    private val binding get() = _binding!!
    private var itemsToAudit : Array<String> = emptyArray()
    private var itemToAudit: String = ""

    companion object {
        private var auditUserFragment: AuditUserFragment? = null
        @JvmStatic
        val instance: AuditUserFragment?
            get() {
                if (auditUserFragment == null) {
                    auditUserFragment = AuditUserFragment()
                }
                return auditUserFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuditUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = AuditController.instance!!.getUserToAudit()

        when(user?.groupName){
            "Preventista" -> itemsToAudit = arrayOf("Inicios de sesion", "Volumen de ventas", "Reportes recomendados")
            "Repartidor" -> itemsToAudit = arrayOf("Inicios de sesion", "Pedidos entregados", "Pedidos no entregados")
            "Administrador" -> itemsToAudit = arrayOf("Listas subidas", "Pedidos confirmados", "Pedidos rechazados")
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.item_rol, itemsToAudit)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerRol.adapter = adapter

        binding.spinnerRol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                itemToAudit = itemsToAudit[position]
                when (itemToAudit) {
                    "Inicios de sesion" -> {
                        AuditController.instance!!.getLogins(user!!.username)
                    }
                    "Volumen de ventas" -> {
                        AuditController.instance!!.getTurnover(user!!.username)
                    }
                    /*"Reportes recomendados" ->*/
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.backButton.setOnClickListener {
            AuditController.instance!!.goToMain()
        }
    }

    fun showFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.container_audit_user, fragment)
        transaction.commit()
    }

}