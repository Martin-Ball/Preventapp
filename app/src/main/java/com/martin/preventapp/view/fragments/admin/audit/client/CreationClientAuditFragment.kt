package com.martin.preventapp.view.fragments.admin.audit.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentCreationClientAuditBinding
import com.martin.preventapp.databinding.FragmentPurchasesClientAuditBinding
import com.martin.preventapp.view.adapter.ClientPurchaseAuditAdapter
import com.martin.preventapp.view.adapter.ReportsAuditAdapter
import com.martin.preventapp.view.entities.ClientPurchaseAudit

class CreationClientAuditFragment : Fragment() {
    private var _binding: FragmentCreationClientAuditBinding? = null
    private val binding get() = _binding!!
    private var dateCreation: String = ""

    companion object {
        private var creationClientAuditFragment: CreationClientAuditFragment? = null

        @JvmStatic
        fun newInstance(date: String): CreationClientAuditFragment {
            if (creationClientAuditFragment == null) {
                creationClientAuditFragment = CreationClientAuditFragment()
            }
            val args = Bundle().apply {
                putString("creationDate", date)
            }
            creationClientAuditFragment?.arguments = args

            return creationClientAuditFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreationClientAuditBinding.inflate(inflater)
        val date = arguments?.getString("creationDate")
        if (date != null) {
            dateCreation = date
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showCreation(dateCreation)
    }

    private fun showCreation(date: String) {
        binding.tvTitleCreation.text = "Fecha de creacion: ${date}"
    }
}