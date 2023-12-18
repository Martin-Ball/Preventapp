package com.martin.preventapp.view.fragments.admin.audit.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martin.preventapp.R
import com.martin.preventapp.databinding.FragmentChangeStateAuditBinding
import com.martin.preventapp.databinding.FragmentRecommendedResponseBinding
import com.martin.preventapp.model.entities.Response.AuditItem
import com.martin.preventapp.model.entities.Response.RecommendedReport
import com.martin.preventapp.view.adapter.ChangeStateAdapter
import com.martin.preventapp.view.adapter.ReportsAuditAdapter

class ChangeStateAuditFragment : Fragment() {
    private var _binding: FragmentChangeStateAuditBinding? = null
    private val binding get() = _binding!!
    private lateinit var auditItem: List<AuditItem>
    private lateinit var adapterReports: ChangeStateAdapter

    companion object {
        private var changeStateAuditFragment: ChangeStateAuditFragment? = null

        @JvmStatic
        fun newInstance(listTurnover: List<AuditItem>): ChangeStateAuditFragment {
            if (changeStateAuditFragment == null) {
                changeStateAuditFragment = ChangeStateAuditFragment()
            }
            val args = Bundle().apply {
                putSerializable("listStates", ArrayList(listTurnover))
            }
            changeStateAuditFragment?.arguments = args

            return changeStateAuditFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeStateAuditBinding.inflate(inflater)
        val changeStateAudit = arguments?.getSerializable("listStates") as? ArrayList<AuditItem>
        if (changeStateAudit != null) {
            auditItem = changeStateAudit
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showList(auditItem)
    }

    private fun showList(list: List<AuditItem>) {
        adapterReports = ChangeStateAdapter(requireContext(), list)
        binding.changeStateList.adapter = adapterReports
    }
}