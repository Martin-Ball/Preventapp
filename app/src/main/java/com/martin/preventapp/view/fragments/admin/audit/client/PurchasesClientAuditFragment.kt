package com.martin.preventapp.view.fragments.admin.audit.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentPurchasesClientAuditBinding
import com.martin.preventapp.databinding.FragmentRecommendedResponseBinding
import com.martin.preventapp.model.entities.Response.RecommendedReport
import com.martin.preventapp.view.adapter.ClientPurchaseAuditAdapter
import com.martin.preventapp.view.adapter.ReportsAuditAdapter
import com.martin.preventapp.view.entities.ClientPurchaseAudit
import com.martin.preventapp.view.fragments.admin.audit.user.RecommendedResponseFragment

class PurchasesClientAuditFragment : Fragment() {
    private var _binding: FragmentPurchasesClientAuditBinding? = null
    private val binding get() = _binding!!
    private lateinit var reports: List<ClientPurchaseAudit>
    private lateinit var adapterClientPurchase: ReportsAuditAdapter

    companion object {
        private var recommendedResponseFragment: PurchasesClientAuditFragment? = null

        @JvmStatic
        fun newInstance(listTurnover: List<ClientPurchaseAudit>): PurchasesClientAuditFragment {
            if (recommendedResponseFragment == null) {
                recommendedResponseFragment = PurchasesClientAuditFragment()
            }
            val args = Bundle().apply {
                putSerializable("purchases", ArrayList(listTurnover))
            }
            recommendedResponseFragment?.arguments = args

            return recommendedResponseFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPurchasesClientAuditBinding.inflate(inflater)
        val reportsList = arguments?.getSerializable("purchases") as? ArrayList<ClientPurchaseAudit>
        if (reportsList != null) {
            reports = reportsList
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showReports(reports)
    }

    private fun showReports(list: List<ClientPurchaseAudit>) {
        val adapterReports = ClientPurchaseAuditAdapter(requireContext(), list)
        binding.clientPurchasesList.adapter = adapterReports
    }
}