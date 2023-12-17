package com.martin.preventapp.view.fragments.admin.audit.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Toast
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.databinding.FragmentClientSelectionBinding
import com.martin.preventapp.databinding.FragmentRecommendedProductBinding
import com.martin.preventapp.databinding.FragmentRecommendedResponseBinding
import com.martin.preventapp.databinding.FragmentTurnoverAuditBinding
import com.martin.preventapp.model.entities.Response.RecommendedReport
import com.martin.preventapp.view.adapter.ReportsAuditAdapter
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Turnover
import com.martin.preventapp.view.fragments.seller.create.ClientSelectionFragment

class RecommendedResponseFragment : Fragment() {
    private var _binding: FragmentRecommendedResponseBinding? = null
    private val binding get() = _binding!!
    private lateinit var reports: List<RecommendedReport>
    private lateinit var adapterReports: ReportsAuditAdapter

    companion object {
        private var recommendedResponseFragment: RecommendedResponseFragment? = null

        @JvmStatic
        fun newInstance(listTurnover: List<RecommendedReport>): RecommendedResponseFragment {
            if (recommendedResponseFragment == null) {
                recommendedResponseFragment = RecommendedResponseFragment()
            }
            val args = Bundle().apply {
                putSerializable("reports", ArrayList(listTurnover))
            }
            recommendedResponseFragment?.arguments = args

            return recommendedResponseFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedResponseBinding.inflate(inflater)
        val reportsList = arguments?.getSerializable("reports") as? ArrayList<RecommendedReport>
        if (reportsList != null) {
            reports = reportsList
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showReports(reports)
    }

    private fun showReports(list: List<RecommendedReport>) {
        adapterReports = ReportsAuditAdapter(requireContext(), list)
        binding.clientRecommendedList.adapter = adapterReports
    }
}