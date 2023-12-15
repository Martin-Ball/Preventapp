package com.martin.preventapp.view.fragments.admin.audit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.databinding.FragmentAuditBinding

class AuditFragment : Fragment() {
    private var _binding: FragmentAuditBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var auditFragment: AuditFragment? = null
        @JvmStatic
        val instance: AuditFragment?
            get() {
                if (auditFragment == null) {
                    auditFragment = AuditFragment()
                }
                return auditFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuditBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAuditUser.setOnClickListener {
            val intent = Intent(requireContext(), AuditActivity::class.java)
            val bundle = Bundle()
            bundle.putString("audit_type", "User")
            intent.putExtras(bundle)
            requireActivity().startActivity(intent)
        }

        binding.btnAuditPrice.setOnClickListener {
            val intent = Intent(requireContext(), AuditActivity::class.java)
            val bundle = Bundle()
            bundle.putString("audit_type", "Price")
            intent.putExtras(bundle)
            requireActivity().startActivity(intent)
        }

        binding.btnAuditClient.setOnClickListener {
            val intent = Intent(requireContext(), AuditActivity::class.java)
            val bundle = Bundle()
            bundle.putString("audit_type", "Client")
            intent.putExtras(bundle)
            requireActivity().startActivity(intent)
        }

        binding.btnAuditOrders.setOnClickListener {
            val intent = Intent(requireContext(), AuditActivity::class.java)
            val bundle = Bundle()
            bundle.putString("audit_type", "Orders")
            intent.putExtras(bundle)
            requireActivity().startActivity(intent)
        }
    }
}